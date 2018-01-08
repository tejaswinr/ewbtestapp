package com.ewb.kafkamessage;

import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractEnqueuer;

public class KafkaMessageEnqueuer extends AbstractEnqueuer<KafkaMessageVO> {

	private boolean keepRunning;

	public KafkaMessageEnqueuer(BlockingQueue<KafkaMessageVO> outboundQueue, int pollTimeout) {
		super(outboundQueue, pollTimeout);
		this.keepRunning = true;
	}

	public KafkaMessageEnqueuer(BlockingQueue<KafkaMessageVO> outboundQueue) {
		super(outboundQueue);
		this.keepRunning = true;
	}

	@Override
	public boolean enqueue(KafkaMessageVO kafkaMessage) {
		try {
			return super.enqueue(kafkaMessage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			keepRunning = false;
		}
		return false;
	}

	protected void executeOnOfferTimeOut() {
		// TODO LOGGER

	}

	@Override
	public boolean keepRunning() {
		return keepRunning;
	}

	public void setKeepRunning(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}
}
