package com.ewb.event;

import com.ewb.event.entity.Event;

public interface EventEnqueuer {

	public void enqueueEvent(Event event);

}
