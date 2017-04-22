package com.huaxia.middleware.sdk.model;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 消息结构对象
 * @author 
 *
 */
public class MessageObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 957017851206682196L;

	/**
	 * 报文头
	 */
	private MessageHeader head;
	
	/**
	 * 报文体
	 */
	private Map<String,Object> body;

	public MessageHeader getHead() {
		if(head==null)
		{
			head=new MessageHeader();
		}
		return head;
	}

	public void setHead(MessageHeader head) {
		this.head = head;
	}

	public Map<String, Object> getBody() {
		if(body==null)
		{
			body=Maps.newHashMap();
		}
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}
	
	
	
}
