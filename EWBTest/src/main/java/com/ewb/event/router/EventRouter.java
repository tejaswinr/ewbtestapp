package com.ewb.event.router;

import com.ewb.event.EventDequeuerImpl;
import com.ewb.event.EventEnqueuerImpl;
import com.ewb.event.entity.Event;

public abstract class EventRouter {

	private EventEnqueuerImpl eventEqueuer;
	private EventDequeuerImpl eventDequeuer;

	public abstract Event pollEvent();

	public abstract void pushEvent(Event event);
}
