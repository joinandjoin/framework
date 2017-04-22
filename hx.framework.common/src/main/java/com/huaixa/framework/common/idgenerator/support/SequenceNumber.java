package com.huaixa.framework.common.idgenerator.support;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.UUID;

import com.huaixa.framework.common.idgenerator.ISequenceNumber;
import com.huaixa.framework.common.utils.CommonUtils;
import com.huaixa.framework.common.utils.DateUtils;

/**
 * 序列值的封装对象
 * 提供通过序列值获取
 * 1、序列值生成时间
 * 2、调用机器所在机房编号
 * 3、调用机器编号
 * 4、序列生成时的自增数值
 * 
 * @author shilei
 *
 */
public class SequenceNumber implements Serializable, ISequenceNumber {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5507624394960363167L;
	private final static long twepoch = 1288834974657L;
	public static final UUID ID_VALUE_NONE = new UUID(0,0);
	
	private long centerId = 0l;
	
	private long workId = 0l;
	
	private long seqNum = 0l;
	
	private long msec;
	
	private long msecSeq;
	
	private String noMsecTime;
	
	private long timestamp = 0l;

	private long idNumber = 0l;
	
	private UUID uuidValue = ID_VALUE_NONE;
	
	public SequenceNumber(long seqNum,long workId, long centerId, long timestamp) {
		super();
		this.centerId = centerId; //5bit
		this.workId = workId;//5bit
		this.timestamp = timestamp;//41bit
		this.seqNum = seqNum;//12bit
		this.idNumber =((this.timestamp - twepoch) << 22) | (this.centerId << 17)
				| (this.workId << 12) | this.seqNum;
		initExtData();
	}
	
	public SequenceNumber(String idValueStr) {
		super();
		if (CommonUtils.isNumber(idValueStr)){
			initData(Long.parseLong(idValueStr));
		}
	}
	
	public SequenceNumber(long idValue) {
		super();
		initData(idValue);
	}

	/**
	 * 以long形式返回当前的数据中心编号
	 * 
	 * @return long 当前的数据中心编号
	 */
	public long getCenterId() {
		return centerId;
	}

	/**
	 * 以long形式返回当前的机器编号
	 * 
	 * @return long 当前的机器编号
	 */
	public long getWorkId() {
		return workId;
	}

	/**
	 * 以long的形式返回序列的生成时间
	 * @return long 序列生成时间的long表示
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * 以Timestamp对象形式返回序列的生成时间
	 * @return Timestamp 序列生成时间的Timestamp对象表示
	 */
	public Timestamp getTimestampAsDate(){
		return new Timestamp(this.getTimestamp());
	}
	
	/**
	 * 以yyyyMMddHHmmssSSS格式返回序列的生成时间
	 * @return String yyyyMMddHHmmssSSS格式的时间字符串
	 */
	public String getTimestampAsString(){
		return DateUtils.formatPattern(new Timestamp(this.getTimestamp()),DateUtils.PATTERN_LONG_DATETIME_DEFAULT_NOLINK);
	}
	
	/**
	 * 以yyyy-MM-dd HH:mm:ss格式返回序列的生成时间
	 * @return String yyyy-MM-dd HH:mm:ss格式的时间字符串
	 */
	public String getDataTimeString(){
		return DateUtils.formatYyyymmddhhmmss(new Timestamp(this.getTimestamp()));
	}
	
	/**
	 * 以yyyyMMddHHmmss格式返回序列的生成时间
	 * @return String yyyyMMddHHmmss格式的时间字符串
	 */
	public String getNoMsecTime(){
		return this.noMsecTime;
	}
	
	/**
	 * 以long形式返回序列生成时的毫秒数
	 * @return long 序列生成时的毫秒数
	 */
	public long getMsec() {
		return this.msec;
	}

	/**
	 * 以long形式返回序列生成时的毫秒数和自增部分的拼接后的数字
	 * @return long 序列生成时的毫秒数和自增部分的拼接后的数字
	 */
	public long getMsecSeq() {
		return this.msecSeq;
	}

	/**
	 * 以long形式返回当前序列值
	 * @return long 序列生值
	 */
	public long getSequence() {
		return idNumber;
	}

	/**
	 * 设置当前序列值为参数指定的值
	 * @param idNumber long 需要设置的序列值
	 */
	public void setSequence(long idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * 以long形式返回当前序列值的自增部分
	 * @return  long 当前序列值的自增部分
	 */
	public long getSeqNum() {
		return seqNum;
	}

	/**
	 * 以UUID方式返回当前的序列值
	 * @return UUID 当前的序列值的UUID表示
	 */
	public UUID getUuidValue() {
		return uuidValue;
	}
	
	public String getBuinessCode(String prefix,String suffix){
		StringBuilder middleCode = new StringBuilder();
		if (CommonUtils.isNotNullEmpty(prefix)){
			middleCode.append(prefix);
		}
		middleCode.append(this.getTimestampAsString())
				.append(this.getCenterId())
				.append(this.getWorkId())
				.append(this.getMsecSeq());
		if (CommonUtils.isNotNullEmpty(suffix)){
			middleCode.append(suffix);
		}
		return middleCode.toString();
	}
	
	public String getBuinessCode(){
		return this.getBuinessCode(null, null);
	}
	
	public String getPreFixBuinessCode(String prefix){
		return this.getBuinessCode(prefix, null);
	}
	
	public String getSuffixFixBuinessCode(String suffix){
		return this.getBuinessCode(null, suffix);
	}
	
	private void initExtData(){
		this.msec  = this.getMsecByTime();
		this.msecSeq = this.getMsecSeqByTime();
		this.noMsecTime = this.getNoMsecStrByTime();
		this.uuidValue = formatUUID();
	}
	
	private void initData(long idValue) {
		this.idNumber = idValue;
		String codeStr = Long.toBinaryString(this.idNumber);
		this.seqNum = new BigInteger(codeStr.substring(codeStr.length()-12,codeStr.length()),2).longValue();
		this.workId = new BigInteger(codeStr.substring(codeStr.length()-17,codeStr.length()-12),2).longValue();
		this.centerId = new BigInteger(codeStr.substring(codeStr.length()-22,codeStr.length()-17),2).longValue();
		this.timestamp = twepoch+new BigInteger(codeStr.substring(0,codeStr.length()-22),2).longValue();
		initExtData();
	}

	private String getNoMsecStrByTime(){
		return DateUtils.formatPattern(new Timestamp(this.getTimestamp()),DateUtils.PATTERN_SHOT_DATETIME_DEFAULT_NOLINK);
	}
	
	private long getMsecByTime(){
		String msec = DateUtils.formatPattern(new Timestamp(this.getTimestamp()),"SSS");
		return Long.parseLong(msec);
	}
	
	private long getMsecSeqByTime(){
		String msecSeq = DateUtils.formatPattern(new Timestamp(this.getTimestamp()),"SSS")+this.getSeqNum();
		return Long.parseLong(msecSeq);
	}
	
	private UUID formatUUID(){
		String hex = String.format("%x",this.getSequence());
		hex = hex+String.format("%1$0"+(32-hex.length())+"d",0);
		StringBuilder sbd = new StringBuilder(hex);
		sbd.insert(8, "-").insert(13, "-").insert(18, "-").insert(23, "-");
		return UUID.fromString(sbd.toString());
	}
	
}
