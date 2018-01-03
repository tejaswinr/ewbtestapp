package com.ewb.kafkamessage;

public interface KafkaMessageEnqueuer extends MessageSender {

	public void enqueueKafkaMessage(KafkaMessage message) throws InterruptedException;

}
