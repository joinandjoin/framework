package com.huaxia.framework.protocol.message.support;

public class HuaxiaHeaders {

	public static final String CONTENT_TYPE_BYTES = "application/octet-stream";

	public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

	public static final String CONTENT_TYPE_SERIALIZED_OBJECT = "application/x-java-serialized-object";

	public static final String CONTENT_TYPE_JSON = "application/json";
	
	public static final String CONTENT_TYPE_XML = "application/xml";
	
	public static final String DEFAULT_CONTENT_TYPE = CONTENT_TYPE_JSON;
	
	
	public static final String ENCRYPTED_MAC = "MAC";
	/**
	 * 版本号
	 */
	public static final String HEADER_VERSION = "version";
	
	public static final String DEFAULT_VERSION = "1.0.0";
	
	
	/**
	 * 传入的交易渠道
	 */
	public static final String HEADER_CHANNEL = "channel";
	
	/**
	 * 传入的设备
	 */
	public static final String HEADER_DEVICE = "device";
	
	/**
	 * 交易日期 格式 yyyyMMddHHmmss
	 */
	public static final String HEADER_TRADETIME = "tradeTime";
	
	/**
	 * 交易流水号，不允许重复，调用方传入，用于后续查询交易状态
	 */
	public static final String HEADER_FLOWID = "flowID";
	
	/**
	 * 交易代码
	 */
	public static final String HEADER_TRADECODE = "tradeCode";
	public static final String DEFAULT_TRADECODE = "88888";
	/**
	 * 交易类型
	 */
	public static final String HEADER_TRADETYPE = "tradeType";
		
	/**
	 * 报文类型 默认JSON格式
	 */
	public static final String HEADER_MSGTYPE = "msgType";
	
	/**
	 * 会话代码 用于前置服务器
	 */
	public static final String HEADER_SESSION = "session";
	
	/**
	 * 身份凭据 用于前置服务器
	 */
	public static final String HEADER_TOKEN = "token";
				
	/**
	 * 响应时间yyyyMMddHHmmss
	 */
	public static final String HEADER_RESPONSE_TIME = "responseTime";
	public static final long DEFAULT_RESPONSE_TIME = -1L;
	
	/**
	 * 响应代码
	 */
	public static final String HEADER_RESPONSE_CODE = "responseCode";
	public static final String DEFAULT_RESPONSE_CODE = "9999";
	
	/**
	 * 响应信息
	 */
	public static final String HEADER_RESPONSE_MSG = "responseMsg";
	public static final String DEFAULT_RESPONSE_MSG = "";
	/**
	 * 交易状态 成功，失败，处理中
	 */
	public static final String HEADER_TRADESTATUS = "tradeStatus";
	public static final String DEFAULT_TRADESTATUS = "2";
	/**
	 * 操作员
	 */
	public static final String HEADER_OPERATOR_ID = "operatorID";
	
	/**
	 * 操作员
	 */
	public static final String HEADER_CREATOR = "creator";
	
	/**
	 * 操作员
	 */
	public static final long DEFAULT_CREATOR = -1L;
}
