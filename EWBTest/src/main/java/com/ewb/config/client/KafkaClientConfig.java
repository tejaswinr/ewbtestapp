package com.ewb.config.client;

import com.ewb.event.entity.EventCategory;
import com.ewb.event.entity.EventType;

public class KafkaClientConfig {

	private String systemId;
	private String clientId;
	private EventType eventType;
	private EventCategory eventCategory;
	private KafkaClientType topicCategory;
	private KafkaTopicConfig topicConfig;
	private int partitionNum;

}
