package com.ewb.event.adapter;

import com.ewb.event.entity.Event;
import com.ewb.event.entity.EventCategory;
import com.ewb.event.entity.EventType;

public class EventFactory {

	public <T> Event createEvent(T message, EventType et, EventCategory ec) {
		return null;
	}

	public <T> T retrieveMessage(Event event) {
		return null;
	}
}
