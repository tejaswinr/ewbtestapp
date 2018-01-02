package com.ewb.event.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event {

	private String eventId;
	private EventType eventType;
	private EventCategory eventCategory;
	private Date createdDateTime;
	private List<EventMessage<? extends Serializable>> eventMessages;

	public Event(String eventId) {
		super();
		this.eventId = eventId;
	}

}
