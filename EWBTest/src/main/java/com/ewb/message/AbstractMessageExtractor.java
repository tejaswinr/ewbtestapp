package com.ewb.message;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.ewb.common.Listener;
import com.ewb.event.Event;
import com.ewb.event.EventMessage;

public abstract class AbstractMessageExtractor<T> implements Listener<Event> {
	private final ExecutorService messageProcService;

	public AbstractMessageExtractor(ExecutorService messageProcService) {
		super();
		this.messageProcService = messageProcService;
	}

	public abstract void processEventMessages(List<EventMessage<? extends Serializable>> eventMessages);

	@Override
	public final void updateListener(final Event event) {
		if (messageProcService != null) {
			messageProcService.submit(() -> {
				List<EventMessage<? extends Serializable>> eventMessages = event.getEventMessages();
				processEventMessages(eventMessages);
			});
		} else {
			// TODO
		}
	}

}
