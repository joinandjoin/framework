package com.huaxia.framework.common.idgenerator.support;

import com.huaxia.framework.common.idgenerator.ISequence;
import com.huaxia.framework.common.idgenerator.ISequenceNumber;
import com.huaxia.framework.common.idgenerator.ITimeSynchronizer;

/**
 * 支持分布式情况使用的序列号生成器
 * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
 */
public class Sequence implements ISequence {
	
	private final static long twepoch = 1288834974657L;
	
	// 机器标识位数
	private final static long workerIdBits = 5L;
	// 数据中心标识位数
	private final static long datacenterIdBits = 5L;
	// 机器ID最大值
	private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
	// 数据中心ID最大值
	private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	// 毫秒内自增位
	private final static long sequenceBits = 12L;
	// 机器ID偏左移12位
	private final static long workerIdShift = sequenceBits;
	// 数据中心ID左移17位
	private final static long datacenterIdShift = sequenceBits + workerIdBits;
	// 时间毫秒左移22位
	private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

	private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

	private static long lastTimestamp = -1L;

	private long sequence = 0L;
	private final long workerId;
	private final long datacenterId;
	
	//@Autowired(required=false)
	private ITimeSynchronizer timeSyncher = null;
	
	/**
	 * 用机房编号和机器编号初始化序列生成器
	 * @param workerId
	 * @param datacenterId
	 */
	public Sequence(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException("worker Id can't be greater than %d or less than 0");
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException("datacenter Id can't be greater than %d or less than 0");
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	@Override
	public ISequenceNumber nextSequence(){
		return new SequenceNumber(nextId());
	}
	
	public static ISequenceNumber getInstance(long idNumber){
		return new SequenceNumber(idNumber);
	}
	
	public static ISequenceNumber getInstance(String idNumber){
		return new SequenceNumber(idNumber);
	}
	
	public static ISequenceNumber getInstance(long seqNum,long workId, long centerId, long timestamp){
		return new SequenceNumber(seqNum,workId,centerId,timestamp);
	}
	
	@Override
	public synchronized long nextId() {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			try {
				throw new Exception("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp)
						+ " milliseconds");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (lastTimestamp == timestamp) {
			// 当前毫秒内，则+1
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				// 当前毫秒内计数满了，则等待下一秒
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0;
		}
		lastTimestamp = timestamp;
		// ID偏移组合生成最终的ID，并返回ID
		long nextId = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;

		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		if (timeSyncher != null){
			return timeSyncher.dateTimeFromLocal();
		}
		return System.currentTimeMillis();
	}

	public ITimeSynchronizer getTimeSyncher() {
		return timeSyncher;
	}

	public void setTimeSyncher(ITimeSynchronizer timeSyncher) {
		this.timeSyncher = timeSyncher;
	}
}
