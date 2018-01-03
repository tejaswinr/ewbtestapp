package com.ewb.event;

import java.io.Serializable;
import java.util.List;

import com.ewb.event.entity.Event;

public abstract class EventGenerator<T extends Serializable> {

	private EventEnqueuerImpl eventEnqueuer;
	private EventFactory eventFactory;

	public void sendEvent(Event event) {

	}

	public Event createEvent(List<T> message) {
		return null;
	}
}
