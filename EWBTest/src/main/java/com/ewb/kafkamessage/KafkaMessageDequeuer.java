package com.ewb.kafkamessage;

public interface KafkaMessageDequeuer extends MessageReceiver {

	public KafkaMessage dequeueKafkaMessage();
}
