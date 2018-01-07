package com.ewb.event.router;

import com.ewb.event.Event;
import com.ewb.event.EventDequeuer;
import com.ewb.event.EventEnqueuer;

public abstract class EventRouter {

	private EventEnqueuer eventEqueuer;
	private EventDequeuer eventDequeuer;

	public abstract Event pollEvent();

	public abstract void pushEvent(Event event);
}
