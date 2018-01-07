package com.ewb.binding;

import com.ewb.common.Dequeuer;
import com.ewb.common.Enqueuer;
import com.ewb.config.client.EventTypeCategoryCache;
import com.ewb.event.Event;
import com.ewb.kafkamessage.KafkaMessage;
import com.ewb.kafkamessage.KafkaMessageFactory;
import com.ewb.kafkamessage.KafkaMessageVO;

public class KafkaEventMessageHelper implements Runnable {

	private final EventTypeCategoryCache evtTypeCatCache = EventTypeCategoryCache.INSTANCE;
	private final Dequeuer<Event> eventDequeuer;
	private final Enqueuer<KafkaMessageVO> kafkaMessageEnqueuer;
	private final KafkaMessageFactory kafkaMessageFactory;
	private boolean keepRunning;

	public KafkaEventMessageHelper(Dequeuer<Event> eventDequeuer, Enqueuer<KafkaMessageVO> kafkaMessageEnqueuer,
			KafkaMessageFactory kafkaMessageFactory) {
		super();
		this.eventDequeuer = eventDequeuer;
		this.kafkaMessageEnqueuer = kafkaMessageEnqueuer;
		this.kafkaMessageFactory = kafkaMessageFactory;
		this.keepRunning = true;
	}

	@Override
	public void run() {
		while (keepRunning) {
			try {
				Event event = dequeueEvent();
				KafkaMessage kafkaMessage = kafkaMessageFactory.createKafkaMessage(event);
				String kafkaTopic = evtTypeCatCache.getTopicName(event.getEventType(), event.getEventCategory());
				if (kafkaTopic == null) {
					// TODO
				}
				enqueueKafkaMessage(kafkaMessage, kafkaTopic);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				keepRunning = false;
			}
		}
	}

	private Event dequeueEvent() throws InterruptedException {
		while (keepRunning) {
			Event event = eventDequeuer.dequeue();
			if (event == null) {
				// LOGGER
				continue;
			}
			return event;
		}
		return null;
	}

	private void enqueueKafkaMessage(KafkaMessage kafkaMessage, String kafkaTopic) throws InterruptedException {
		while (keepRunning) {
			boolean status = kafkaMessageEnqueuer.enqueue(new KafkaMessageVO(kafkaMessage, kafkaTopic));
			if (!status) {
				// LOGGER
				continue;
			}
		}
	}
}
