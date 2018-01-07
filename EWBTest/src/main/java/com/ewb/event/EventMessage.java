package com.ewb.event;

import java.io.Serializable;

public class EventMessage<T extends Serializable> {

	private final String messageId;
	private final T message;

	public EventMessage(String messageId, T message) {
		super();
		this.messageId = messageId;
		this.message = message;
	}

	public String getMessageId() {
		return messageId;
	}

	public T getMessage() {
		return message;
	}

}
