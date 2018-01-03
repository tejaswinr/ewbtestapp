package com.ewb.event;

import java.util.concurrent.BlockingQueue;

import com.ewb.event.entity.Event;

public class EventEnqueuerImpl implements EventEnqueuer {
	private final BlockingQueue<Event> eventQueue;

	public EventEnqueuerImpl(BlockingQueue<Event> eventQueue) {
		super();
		this.eventQueue = eventQueue;
	}

	@Override
	public void enqueueEvent(Event event) {
		// TODO Auto-generated method stub

	}

}
