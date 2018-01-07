package com.ewb.config.client;

import java.util.Map;
import java.util.TreeMap;

import com.ewb.event.EventCategory;
import com.ewb.event.EventType;

public enum EventTypeCategoryCache {
	INSTANCE;

	private final Map<EventCategoryTypeKey, KafkaTopicConfig> eventTypeCatTopicConfigMap = new TreeMap<>();

	private static EventCategoryTypeKey createEventCategoryTypeKey(EventType eventType, EventCategory eventCategory) {
		return new EventCategoryTypeKey(eventType, eventCategory);
	}

	public void addEventTypeCatTopicConfig(EventType eventType, EventCategory eventCategory,
			KafkaTopicConfig kafkaTopicConfig) {
		eventTypeCatTopicConfigMap.put(createEventCategoryTypeKey(eventType, eventCategory), kafkaTopicConfig);
	}

	public void removeEventTypeCatTopicConfig(EventType eventType, EventCategory eventCategory) {
		eventTypeCatTopicConfigMap.remove(createEventCategoryTypeKey(eventType, eventCategory));
	}

	public String getTopicName(EventType eventType, EventCategory eventCategory) {
		KafkaTopicConfig kafkaTopicConfig = eventTypeCatTopicConfigMap
				.get(createEventCategoryTypeKey(eventType, eventCategory));
		if (kafkaTopicConfig != null) {
			return kafkaTopicConfig.getTopicName();
		}
		return null;
	}

}

class EventCategoryTypeKey {
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
