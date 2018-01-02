package com.ewb.event.router;

import com.ewb.event.adapter.EventDequeuer;
import com.ewb.event.adapter.EventEnqueuer;
import com.ewb.event.entity.Event;

public abstract class EventRouter {

	private EventEnqueuer eventEqueuer;
	private EventDequeuer eventDequeuer;

	public abstract Event pollEvent();

	public abstract void pushEvent(Event event);
}
