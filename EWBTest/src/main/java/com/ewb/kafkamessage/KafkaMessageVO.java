package com.ewb.kafkamessage;

public final class KafkaMessageVO {

	private final KafkaMessage kafkaMessage;
	private final String kafkaTopic;

	public KafkaMessageVO(KafkaMessage kafkaMessage, String kafkaTopic) {
		super();
		this.kafkaMessage = kafkaMessage;
		this.kafkaTopic = kafkaTopic;
	}

	public KafkaMessage getKafkaMessage() {
		return kafkaMessage;
	}

	public String getKafkaTopic() {
		return kafkaTopic;
	}

}
