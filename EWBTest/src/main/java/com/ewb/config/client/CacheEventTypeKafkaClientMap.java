package com.ewb.config.client;

import java.util.Map;

import com.ewb.event.adapter.EWBKafkaConsumer;
import com.ewb.event.adapter.EWBKafkaProducer;
import com.ewb.event.entity.EventCategory;
import com.ewb.event.entity.EventType;

public class CacheEventTypeKafkaClientMap {

	private Map<EventCategoryTypeKey, EWBKafkaProducer> eventTypeProducerMap;
	private Map<EventCategoryTypeKey, EWBKafkaConsumer> eventTypeConsumerMap;

	public EWBKafkaProducer getEWBKafkaProducer(EventType eventType, EventCategory eventCategory) {
		return null;
	}

	public EWBKafkaConsumer getEWBKafkaConsumer(EventType eventType, EventCategory eventCategory) {
		return null;
	}

	public void updateEWBKafkaProducer(EventType eventType, EventCategory eventCategory, EWBKafkaProducer producer) {
	}

	public void updateEWBKafkaConsumer(EventType eventType, EventCategory eventCategory, EWBKafkaConsumer consumer) {
	}
}

class EventCategoryTypeKey {
	private EventType eventType;
	private EventCategory eventCategory;
}
