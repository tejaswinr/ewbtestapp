package com.ewb.kafkamessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class KafkaMessageEnqueuerImpl implements KafkaMessageEnqueuer {
	private final BlockingQueue<KafkaMessage> messageQueue;
	private int offerTimeout;

	public KafkaMessageEnqueuerImpl(BlockingQueue<KafkaMessage> messageQueue) {
		super();
		this.messageQueue = messageQueue;
	}

	public KafkaMessageEnqueuerImpl(BlockingQueue<KafkaMessage> messageQueue, int offerTimeout) {
		super();
		this.messageQueue = messageQueue;
		this.offerTimeout = offerTimeout;
	}

	@Override
	public void enqueueKafkaMessage(KafkaMessage message) throws InterruptedException {
		messageQueue.offer(message, offerTimeout, TimeUnit.SECONDS);

	}

}
