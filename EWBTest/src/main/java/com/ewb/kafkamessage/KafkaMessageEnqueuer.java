package com.ewb.kafkamessage;

import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractEnqueuer;

public class KafkaMessageEnqueuer extends AbstractEnqueuer<KafkaMessageVO> {

	public KafkaMessageEnqueuer(BlockingQueue<KafkaMessageVO> outboundQueue, int pollTimeout) {
		super(outboundQueue, pollTimeout);
	}

	public KafkaMessageEnqueuer(BlockingQueue<KafkaMessageVO> outboundQueue) {
		super(outboundQueue);
	}

	@Override
	public boolean enqueue(KafkaMessageVO kafkaMessage) {
		try {
			return super.enqueue(kafkaMessage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
}
