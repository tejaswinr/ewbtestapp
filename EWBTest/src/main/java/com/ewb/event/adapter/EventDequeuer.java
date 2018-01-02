package com.ewb.event.adapter;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.ewb.event.entity.Event;
import com.ewb.event.entity.KafkaMessage;

public class EventDequeuer {

	private List<EventListener> eventListeners;
	private BlockingQueue<KafkaMessage> consumerQueue;
	private KafkaMessageFactory kafkaMessageFactory;

	public void dequeueEvent(Event event) {

	}

	public void updateListeners() {

	}

	public void registerEventListener(EventListener listener) {

	}

}
