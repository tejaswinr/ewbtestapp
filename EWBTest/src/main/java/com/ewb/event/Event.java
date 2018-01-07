package com.ewb.event;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event {

	private final String eventId;
	private EventType eventType;
	private EventCategory eventCategory;
	private Date createdDateTime;
	private List<EventMessage<? extends Serializable>> eventMessages;

	public Event(String eventId) {
		super();
		this.eventId = eventId;
	}

	public Event(String eventId, EventType eventType, EventCategory eventCategory) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventCategory = eventCategory;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public List<EventMessage<? extends Serializable>> getEventMessages() {
		return eventMessages;
	}

	public void setEventMessages(List<EventMessage<? extends Serializable>> eventMessages) {
		this.eventMessages = eventMessages;
	}

	public String getEventId() {
		return eventId;
	}

}
