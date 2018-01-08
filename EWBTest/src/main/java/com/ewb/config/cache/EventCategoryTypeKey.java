package com.ewb.config.cache;

import com.ewb.event.EventCategory;
import com.ewb.event.EventType;

public class EventCategoryTypeKey {
	private final EventType eventType;
	private final EventCategory eventCategory;

	public EventCategoryTypeKey(EventType eventType, EventCategory eventCategory) {
		super();
		this.eventType = eventType;
		this.eventCategory = eventCategory;
	}

	public EventType getEventType() {
		return eventType;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventCategory == null) ? 0 : eventCategory.hashCode());
		result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventCategoryTypeKey other = (EventCategoryTypeKey) obj;
		if (eventCategory != other.eventCategory)
			return false;
		if (eventType != other.eventType)
			return false;
		return true;
	}

}