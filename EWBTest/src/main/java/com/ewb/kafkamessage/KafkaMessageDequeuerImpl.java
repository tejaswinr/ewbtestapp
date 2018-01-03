package com.ewb.kafkamessage;

import java.util.concurrent.BlockingQueue;

public class KafkaMessageDequeuerImpl implements KafkaMessageDequeuer {
	private final BlockingQueue<KafkaMessage> messageQueue;
	private int pollTimeout;

	public KafkaMessageDequeuerImpl(BlockingQueue<KafkaMessage> messageQueue) {
		super();
		this.messageQueue = messageQueue;
	}

	public KafkaMessageDequeuerImpl(BlockingQueue<KafkaMessage> messageQueue, int pollTimeout) {
		super();
		this.messageQueue = messageQueue;
		this.pollTimeout = pollTimeout;
	}

	@Override
	public KafkaMessage dequeueKafkaMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
