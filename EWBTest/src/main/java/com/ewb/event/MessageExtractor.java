package com.ewb.event;

import java.util.List;

import com.ewb.event.entity.Event;

public abstract class MessageExtractor<T> {
	private EventDequeuerImpl eventDequeuer;
	private EventFactory eventFactory;
	private EventListener listener;

	public abstract <T> List<T> extractMessageList(Event event);
}
