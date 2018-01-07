package com.ewb.config.client;

public final class KafkaTopicConfig {

	private final String topicId;
	private final String topicName;
	private final String keySerializer;
	private final String keyDeserializer;
	private final String valueSerializer;
	private final String valueDeserializer;
	private final boolean enableCompression;
	private final String compressionCodec;

	public KafkaTopicConfig(String topicId, String topicName) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.keySerializer = null;
		this.keyDeserializer = null;
		this.valueSerializer = null;
		this.valueDeserializer = null;
		this.enableCompression = false;
		this.compressionCodec = null;
	}

	public KafkaTopicConfig(String topicId, String topicName, String keySerializer, String keyDeserializer,
			String valueSerializer, String valueDeserializer, boolean enableCompression, String compressionCodec) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.keySerializer = keySerializer;
		this.keyDeserializer = keyDeserializer;
		this.valueSerializer = valueSerializer;
		this.valueDeserializer = valueDeserializer;
		this.enableCompression = enableCompression;
		this.compressionCodec = compressionCodec;
	}

	public String getTopicId() {
		return topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public String getKeySerializer() {
		return keySerializer;
	}

	public String getKeyDeserializer() {
		return keyDeserializer;
	}

	public String getValueSerializer() {
		return valueSerializer;
	}

	public String getValueDeserializer() {
		return valueDeserializer;
	}

	public boolean isEnableCompression() {
		return enableCompression;
	}

	public String getCompressionCodec() {
		return compressionCodec;
	}

}
