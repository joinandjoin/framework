package com.huaixa.framework.common.idgenerator;

import java.sql.Timestamp;
import java.util.UUID;

public interface ISequenceNumber {

	/**
	 * 获取机房编号
	 * @return long 机房编号
	 */
	long getCenterId();

	/**
	 * 获取机器编号
	 * @return long 机器编号
	 */
	long getWorkId();

	/**
	 * 返回自增字段当前值
	 * @return long 自增字段当前值
	 */
	long getSeqNum();

	/**
	 * 返回当前序列值
	 * @return long 序列值
	 */
	long getSequence();

	/**
	 * 设置当前序列值 ，
	 * @param long 时间戳的long值
	 *
	 */
	void setSequence(long sequence);

	/**
	 * 返回时间戳的long表示
	 * @return long 时间戳的long表示
	 */
	long getTimestamp();

	/**
	 * 返回时间戳的Timestamp表示
	 * @return Timestamp 时间戳的Timestamp表示
	 */
	Timestamp getTimestampAsDate();

	/**
	 * 返回时间戳的String表示
	 * @return String 时间戳的String表示,格式为yyyy-MM-dd HH:mm:ss
	 */
	String getTimestampAsString();

	/**
	 * 返回时间戳的String表示
	 * @return String 时间戳的String表示,格式为yyyyMMddHHmmssSS
	 */
	String getDataTimeString();

	public UUID getUuidValue();
	
	/**
	 * 以yyyyMMddHHmmss格式返回序列的生成时间
	 * @return String yyyyMMddHHmmss格式的时间字符串
	 */
	String getNoMsecTime();
	
	/**
	 * 以long形式返回序列生成时的毫秒数
	 * @return long 序列生成时的毫秒数
	 */
	long getMsec();

	/**
	 * 以long形式返回序列生成时的毫秒数和自增部分的拼接后的数字
	 * @return long 序列生成时的毫秒数和自增部分的拼接后的数字
	 */
	long getMsecSeq();

	/**
	 * 通过当前序列值获取String形式的业务编码，可以与前缀和后缀
	 * @param prefix String 业务编码前缀
	 * @param suffix String 业务编码后缀
	 * @return String 序列相关的带前后缀业务编码
	 */
	String getBuinessCode(String prefix,String suffix);
	
	/**
	 * 通过当前序列值获取String形式的业务编码，没有前后缀
	 * @return String 序列相关的无前后缀业务编码
	 */
	String getBuinessCode();
	
	/**
	 * 通过当前序列值获取String形式的带前缀的业务编码
	 * @param prefix String 业务编码前缀
	 * @return String 序列相关的带前缀的业务编码
	 */
	String getPreFixBuinessCode(String prefix);
	
	/**
	 * 通过当前序列值获取String形式的带后缀的业务编码
	 * @param suffix String 业务编码后缀
	 * @return String 序列相关的带后缀的业务编码
	 */
	String getSuffixFixBuinessCode(String suffix);
	
}