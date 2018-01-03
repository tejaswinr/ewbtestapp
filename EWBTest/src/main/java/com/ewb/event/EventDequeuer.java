package com.ewb.event;

import com.ewb.event.entity.Event;

public interface EventDequeuer {

	public Event dequeueEvent();

}
