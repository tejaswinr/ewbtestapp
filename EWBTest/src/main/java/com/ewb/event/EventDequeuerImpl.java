package com.ewb.event;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.ewb.event.entity.Event;
import com.ewb.kafkamessage.KafkaMessage;

public class EventDequeuerImpl implements EventDequeuer {

	private List<EventListener> eventListeners;
	private BlockingQueue<KafkaMessage> consumerQueue;

	public void updateListeners() {

	}

	public void registerEventListener(EventListener listener) {

	}

	@Override
	public Event dequeueEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
