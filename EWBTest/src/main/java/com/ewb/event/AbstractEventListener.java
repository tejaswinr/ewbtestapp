package com.ewb.event;

import java.util.concurrent.ExecutorService;

import com.ewb.common.Listener;

public abstract class AbstractEventListener implements Listener<Event> {

	private final ExecutorService eventProcService;

	public AbstractEventListener(ExecutorService eventProcService) {
		super();
		this.eventProcService = eventProcService;
	}

	@Override
	public final void updateListener(final Event event) {
		if (eventProcService != null) {
			eventProcService.submit(() -> processEvent(event));
		} else {
			// TODO
		}
	}

	protected abstract void processEvent(Event event);

}
