package com.huaxia.framework.protocol.message;

import org.springframework.messaging.Message;

public interface HandlerInterceptor {

	/**
	 * Invoked before the Message is actually sent to the channel.
	 * This allows for modification of the Message if necessary.
	 * If this method returns {@code null} then the actual
	 * send invocation will not occur.
	 */
	Message<?> preSend(Message<?> message);

	/**
	 * Invoked immediately after the send invocation. The boolean
	 * value argument represents the return value of that invocation.
	 */
	void postSend(Message<?> message, boolean sent);

	/**
	 * Invoked after the completion of a send regardless of any exception that
	 * have been raised thus allowing for proper resource cleanup.
	 * <p>Note that this will be invoked only if {@link #preSend} successfully
	 * completed and returned a Message, i.e. it did not return {@code null}.
	 * @since 4.1
	 */
	void afterSendCompletion(Message<?> message, boolean sent, Exception ex);

	/**
	 * Invoked as soon as receive is called and before a Message is
	 * actually retrieved. If the return value is 'false', then no
	 * Message will be retrieved. This only applies to PollableChannels.
	 */
	boolean preReceive();

	/**
	 * Invoked immediately after a Message has been retrieved but before
	 * it is returned to the caller. The Message may be modified if
	 * necessary. This only applies to PollableChannels.
	 */
	Message<?> postReceive(Message<?> message);

	/**
	 * Invoked after the completion of a receive regardless of any exception that
	 * have been raised thus allowing for proper resource cleanup.
	 * <p>Note that this will be invoked only if {@link #preReceive} successfully
	 * completed and returned {@code true}.
	 * @since 4.1
	 */
	void afterReceiveCompletion(Message<?> message, Exception ex);

}
