package com.ewb.config.init;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.ewb.common.Dequeuer;
import com.ewb.common.Enqueuer;
import com.ewb.event.Event;
import com.ewb.event.EventDequeuer;
import com.ewb.event.EventEnqueuer;
import com.ewb.kafkamessage.KafkaMessageDequeuer;
import com.ewb.kafkamessage.KafkaMessageEnqueuer;
import com.ewb.kafkamessage.KafkaMessageVO;

public class InitOutboundConfig {

	/*
	 * 
	 * outbound:
	 * 
	 * outBoundEventEnqueuer: outboundQueue1, offerTimeout
	 * outBoundKafkaMessageEnqueuer: outboundQueue2, offerTimeout
	 * 
	 * outBoundEventDequeuer: outboundQueue1, pollTimeout (thread)
	 * outBoundKafkaMessageDequeuer: outboundQueue2, pollTimeout (thread)
	 * 
	 * 
	 * registerEventListeners: executorService (KafkaEventOutboundHelper)
	 * registerKafkaMessageListeners: executorService (KafkaProducerWorker)
	 * 
	 * 
	 * KafkaEventOutboundHelper (kafkaMessageEnqueuer, kafkaMessageFactory)
	 */

	private Enqueuer<Event> outboundEventEnqueuer(BlockingQueue<Event> eventQueue, int pollTimeout) {
		return new EventEnqueuer(eventQueue, pollTimeout);
	}

	private Enqueuer<KafkaMessageVO> outboundMessageDequeuer(BlockingQueue<KafkaMessageVO> messageQueue,
			int pollTimeout) {
		return new KafkaMessageEnqueuer(messageQueue, pollTimeout);
	}

	private Dequeuer<Event> outboundEventDequeuer(BlockingQueue<Event> eventQueue, int pollTimeout) {
		return new EventDequeuer(eventQueue, pollTimeout);
	}

	private Dequeuer<KafkaMessageVO> outboundKafkaMessageDequeuer(BlockingQueue<KafkaMessageVO> messageQueue,
			int pollTimeout) {
		return new KafkaMessageDequeuer(messageQueue, pollTimeout);
	}

	private BlockingQueue<Event> createOutBoundEventQueue() {
		return new LinkedBlockingQueue<>();
	}

	private BlockingQueue<Event> createOutBoundKafkaMessageQueue() {
		return new LinkedBlockingQueue<>();
	}

}
