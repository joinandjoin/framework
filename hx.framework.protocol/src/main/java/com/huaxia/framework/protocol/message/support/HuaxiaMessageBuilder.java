package com.huaxia.framework.protocol.message.support;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.MimeType;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 创建消息工具类，定义消息负载为byte[]类型
 * 公用消息头：
 *
 * 消息头 id 属性，唯一标识消息，UUID类型；
 * 
 * 消息头 timestamp 属性，消息创建时间，默认当前时间戳；
 * 
 * 消息头  contentType 属性，消息的格式类型默认为application/json
 * 
 * 消息头 replyChannel 属性，消息的回复渠道标识，也可以理解为请求渠道即消息接受者处理结果的返回渠道，Object类型
 * 
 * 消息头 errorChannel 属性，消息处理发生错误时回复渠道标识，Object类型
 * 
 * 自定义消息头：
 * 
 * device ： 消息来源设备类型，String 类型 pc，android，iphone
 * creator : 消息创建者标识，long类型
 * version ：  消息版本，用于标识消息升级，String 类型 ，默认1.0.0
 * responseTime ：  消息回复时间，long类型，默认当前时间戳。
 * responseMsg : 消息处理返回描述，String 类型
 * responseCode ：  消息处理回复代码，String 类型；默认 9999-处理失败,0000-处理成功，6666-处理异常，9998-接口停用
 * tradeStatus ： 业务处理结果返回码，String 类型 ；默认 0-业务处理未返回结果；7-业务处理出现错误；6-业务处理出现异常；1-业务处理成功；
 * 				 2-业务处理失败；3-业务处理中
 * 
 * tradeCode : 交易代码，标识具体业务交易，String 类型。如果为restful的http请求，则可以为空
 * mac/session/token : 加密报文字段，String类型。如果为OAuth2或者open_id类型验证，则可以为空
 * @author shilei
 *
 */
public class HuaxiaMessageBuilder {

	public static AlternativeJdkIdGenerator idGenerator = new AlternativeJdkIdGenerator();
	
	public static Message<byte[]> createDefaultRequestMessage(String channel, String device, String tradCode, 
			byte[] payload) {
		MessageHeaderAccessor accessor = huaxiaMessageHeaderAccessor();
		
		accessor.setReplyChannelName(channel);
		accessor.setErrorChannelName(channel);
		accessor.setHeader(HuaxiaHeaders.HEADER_DEVICE, device);
		accessor.setHeader(HuaxiaHeaders.HEADER_TRADECODE, tradCode);
		
		accessor.setImmutable();
		MessageBuilder<byte[]>  builder = MessageBuilder.withPayload(payload).setHeaders(accessor);
		return builder.build();
	}
	
	public static Message<byte[]> createResponseMessage(Message<byte[]> message, String responseCode, String tradStatus, 
			String responseMsg, byte[] payload) {
		MessageHeaderAccessor accessor = MessageHeaderAccessor.getMutableAccessor(message);
		
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_TIME, System.currentTimeMillis());
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_CODE, responseCode);
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_MSG, responseMsg);
		accessor.setHeader(HuaxiaHeaders.HEADER_TRADESTATUS, tradStatus);

		accessor.setImmutable();
		
		MessageBuilder<byte[]>  builder = MessageBuilder.withPayload(payload).setHeaders(accessor);
		return builder.build();
	}
	
	public static Message<byte[]> createResponseMessage(Message<byte[]> message, String responseCode, String tradStatus, 
			String responseMsg) {
		
		MessageHeaderAccessor accessor = MessageHeaderAccessor.getMutableAccessor(message);
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_TIME, System.currentTimeMillis());
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_CODE, responseCode);
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_MSG, responseMsg);
		accessor.setHeader(HuaxiaHeaders.HEADER_TRADESTATUS, tradStatus);
		
		accessor.setImmutable();
		MessageBuilder<byte[]>  builder = MessageBuilder.fromMessage(message).setHeaders(accessor);
		return builder.build();
	}
	
	public static MessageHeaderAccessor huaxiaMessageHeaderAccessor(){
		
		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		MimeType contentType = MimeType.valueOf(HuaxiaHeaders.DEFAULT_CONTENT_TYPE);
		accessor.setContentType(contentType);
		accessor.setHeader(MessageHeaders.CONTENT_TYPE, HuaxiaHeaders.DEFAULT_CONTENT_TYPE);
		accessor.setHeader(HuaxiaHeaders.HEADER_VERSION, HuaxiaHeaders.DEFAULT_VERSION);
		accessor.setHeader(HuaxiaHeaders.HEADER_CREATOR, HuaxiaHeaders.DEFAULT_CREATOR);
		
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_TIME, HuaxiaHeaders.DEFAULT_RESPONSE_TIME);
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_CODE, HuaxiaHeaders.DEFAULT_RESPONSE_CODE);
		accessor.setHeader(HuaxiaHeaders.HEADER_RESPONSE_MSG, HuaxiaHeaders.DEFAULT_RESPONSE_MSG);
		
		accessor.setHeader(HuaxiaHeaders.HEADER_TRADECODE, HuaxiaHeaders.DEFAULT_TRADECODE);
		accessor.setHeader(HuaxiaHeaders.HEADER_TRADESTATUS, HuaxiaHeaders.DEFAULT_TRADESTATUS);
		accessor.setHeader(HuaxiaHeaders.HEADER_FLOWID, idGenerator.generateId());
		
		return accessor;
	}
	
	public static void main(String[] args) throws Exception {
		Message<byte[]> message = createDefaultRequestMessage("sudai","android","apply_order","记得看房价大幅度发".getBytes());
		ObjectMapper mapper = new ObjectMapper();
		String jjj = mapper.writeValueAsString(message);
		System.out.println(jjj);
		Message<byte[]> remessage = createResponseMessage(message,"9999","7","jkjkkkjjk","就看就看就看就看空间".getBytes());
		System.out.println(mapper.writeValueAsString(remessage));
	}
}
