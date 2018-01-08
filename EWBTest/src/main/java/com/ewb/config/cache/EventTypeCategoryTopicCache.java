package com.ewb.config.cache;

import java.util.Map;
import java.util.TreeMap;

import com.ewb.config.client.KafkaTopicConfig;
import com.ewb.event.EventCategory;
import com.ewb.event.EventType;

public enum EventTypeCategoryTopicCache {
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
