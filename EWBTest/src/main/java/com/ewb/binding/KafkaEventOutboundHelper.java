package com.ewb.binding;

import java.util.concurrent.ExecutorService;

import com.ewb.common.Enqueuer;
import com.ewb.config.cache.EventTypeCategoryTopicCache;
import com.ewb.event.AbstractEventListener;
import com.ewb.event.Event;
import com.ewb.kafkamessage.KafkaMessage;
import com.ewb.kafkamessage.KafkaMessageFactory;
import com.ewb.kafkamessage.KafkaMessageVO;

public class KafkaEventOutboundHelper extends AbstractEventListener {

	private final EventTypeCategoryTopicCache evtTypeCatCache = EventTypeCategoryTopicCache.INSTANCE;
	private final Enqueuer<KafkaMessageVO> kafkaMessageEnqueuer;
	private final KafkaMessageFactory kafkaMessageFactory;

	public KafkaEventOutboundHelper(ExecutorService eventProcService, Enqueuer<KafkaMessageVO> kafkaMessageEnqueuer,
			KafkaMessageFactory kafkaMessageFactory) {
		super(eventProcService);
		this.kafkaMessageEnqueuer = kafkaMessageEnqueuer;
		this.kafkaMessageFactory = kafkaMessageFactory;
	}

	@Override
	protected void processEvent(Event event) {
		try {
			KafkaMessage kafkaMessage = kafkaMessageFactory.createKafkaMessage(event);
			String kafkaTopic = evtTypeCatCache.getTopicName(event.getEventType(), event.getEventCategory());
			if (kafkaTopic == null) {
				// TODO
			}
			kafkaMessageEnqueuer.enqueue(new KafkaMessageVO(kafkaMessage, kafkaTopic));
		} catch (InterruptedException e) {
			// TODO
		}
	}

}
