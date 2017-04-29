package com.huaxia.framework.protocol.message.support;

import org.springframework.messaging.Message;

import com.huaxia.framework.protocol.message.HandlerInterceptor;

public abstract class HandlerInterceptorAdapter implements HandlerInterceptor {

	@Override
	public Message<?> preSend(Message<?> message) {
		return message;
	}

	@Override
	public void postSend(Message<?> message, boolean sent) {

	}

	@Override
	public void afterSendCompletion(Message<?> message, boolean sent, Exception ex) {
	
	}

	@Override
	public boolean preReceive() {
		return true;
	}

	@Override
	public Message<?> postReceive(Message<?> message) {
		return message;
	}

	@Override
	public void afterReceiveCompletion(Message<?> message, Exception ex) {
	}

}
