package com.ewb.config.client;

import com.ewb.event.EventCategory;
import com.ewb.event.EventType;

public class KafkaClientConfig {

	private String systemId;
	private String clientId;
	private EventType eventType;
	private EventCategory eventCategory;
	private KafkaClientType topicCategory;
	private KafkaTopicConfig topicConfig;
	private int partitionNum;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public KafkaClientType getTopicCategory() {
		return topicCategory;
	}

	public void setTopicCategory(KafkaClientType topicCategory) {
		this.topicCategory = topicCategory;
	}

	public KafkaTopicConfig getTopicConfig() {
		return topicConfig;
	}

	public void setTopicConfig(KafkaTopicConfig topicConfig) {
		this.topicConfig = topicConfig;
	}

	public int getPartitionNum() {
		return partitionNum;
	}

	public void setPartitionNum(int partitionNum) {
		this.partitionNum = partitionNum;
	}

}
