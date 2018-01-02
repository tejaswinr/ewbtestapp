package com.ewb.config.client;

public class KafkaTopicConfig {

	private String topicId;
	private String topicName;
	private String keySerializer;
	private String keyDeserializer;
	private String valueSerializer;
	private String valueDeserializer;
	private boolean enableCompression;
	private String compressionCodec;

}
