package com.huaxia.framework.protocol;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.HeaderMapper;
import org.springframework.util.StringUtils;

import com.huaixa.framework.common.utils.DateUtils;
import com.huaxia.middleware.sdk.model.MessageObject;

public class HuaxiaHeaderMapper implements HeaderMapper<MessageObject> {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Override
	public void fromHeaders(MessageHeaders headers, MessageObject target) {
		
	}

	@Override
	public MessageHeaders toHeaders(MessageObject source) {
		Map<String, Object> headers = new LinkedHashMap<String, Object>();
		try {
			String msgType = source.getHead().getMsgType();
			if (StringUtils.hasText(msgType)) {
				headers.put(MessageHeaders.CONTENT_TYPE, msgType);
			} else {
				headers.put(MessageHeaders.CONTENT_TYPE, HuaxiaHeaders.DEFAULT_CONTENT_TYPE);
			}
			
			String channel = source.getHead().getChannel();
			if (StringUtils.hasText(channel)) {
				headers.put(MessageHeaders.REPLY_CHANNEL, channel);
			}
			
			String tradeTime = source.getHead().getTradeTime();
			if (StringUtils.hasText(tradeTime)) {
				headers.put(MessageHeaders.TIMESTAMP, DateUtils.getDate(tradeTime,DateUtils.PATTERN_SHOT_DATETIME_DEFAULT_NOLINK));
			}
			
			String device = source.getHead().getDevice();
			if (StringUtils.hasText(device)) {
				headers.put(HuaxiaHeaders.HEADER_DEVICE,device);
			}
			
			String flowId = source.getHead().getFlowID();
			if (StringUtils.hasText(flowId)) {
				headers.put(HuaxiaHeaders.HEADER_FLOWID,flowId);
			}
			
			String operatorId = source.getHead().getOperatorID();
			if (StringUtils.hasText(operatorId)) {
				headers.put(HuaxiaHeaders.HEADER_OPERATOR_ID,operatorId);
			}
			
			String responseCode = source.getHead().getResponseCode();
			if (StringUtils.hasText(responseCode)) {
				headers.put(HuaxiaHeaders.HEADER_RESPONSE_CODE,responseCode);
			}
			
			String responseMsg = source.getHead().getResponseMsg();
			if (StringUtils.hasText(responseMsg)) {
				headers.put(HuaxiaHeaders.HEADER_RESPONSE_MSG,responseMsg);
			}
			
			String responseTime = source.getHead().getResponseTime();
			if (StringUtils.hasText(responseTime)) {
				headers.put(HuaxiaHeaders.HEADER_RESPONSE_TIME,responseTime);
			}
			
			String session = source.getHead().getSession();
			if (StringUtils.hasText(session)) {
				headers.put(HuaxiaHeaders.HEADER_SESSION,session);
			}
			
			String token = source.getHead().getToken();
			if (StringUtils.hasText(token)) {
				headers.put(HuaxiaHeaders.HEADER_TOKEN,token);
			}
			
			String tradeCode = source.getHead().getTradeCode();
			if (StringUtils.hasText(tradeCode)) {
				headers.put(HuaxiaHeaders.HEADER_TRADECODE,tradeCode);
			}
			
			String tradeStatus = source.getHead().getTradeStatus();
			if (StringUtils.hasText(tradeStatus)) {
				headers.put(HuaxiaHeaders.HEADER_TRADESTATUS,tradeStatus);
			}
			
			String tradeType = source.getHead().getTradeType();
			if (StringUtils.hasText(tradeType)) {
				headers.put(HuaxiaHeaders.HEADER_TRADETYPE,tradeType);
			}
			
			String version = source.getHead().getVersion();
			if (StringUtils.hasText(version)) {
				headers.put(HuaxiaHeaders.HEADER_VERSION,version);
			}
			
		} catch(Exception e){
			if (logger.isWarnEnabled()) {
				logger.warn("将 MessageObject 对象转为MessageHeaders时出现错误", e);
			}
		}
		return new MessageHeaders(headers);
	}
}
