package com.ewb.binding;

import java.util.concurrent.ExecutorService;

import com.ewb.common.Enqueuer;
import com.ewb.event.Event;
import com.ewb.kafkamessage.AbstractKafkaMessageListener;
import com.ewb.kafkamessage.KafkaMessageVO;

public class KafkaEventInboundHelper extends AbstractKafkaMessageListener {

	private final Enqueuer<Event> eventEnqueuer;

	public KafkaEventInboundHelper(ExecutorService kafkaMsgProcService, Enqueuer<Event> eventEnqueuer) {
		super(kafkaMsgProcService);
		this.eventEnqueuer = eventEnqueuer;
	}

	@Override
	protected void processKafkaMessage(KafkaMessageVO kafkaMessage) {
		// TODO Auto-generated method stub
		Event event = null;
		try {
			eventEnqueuer.enqueue(event);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
