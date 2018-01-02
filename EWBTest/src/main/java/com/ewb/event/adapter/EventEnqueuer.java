package com.ewb.event.adapter;

import java.util.concurrent.BlockingQueue;

import com.ewb.event.entity.Event;
import com.ewb.event.entity.KafkaMessage;

public class EventEnqueuer {
	private BlockingQueue<KafkaMessage> producerQueue;
	private KafkaMessageFactory kafkaMessageFactory;

	public void enqueueEvent(Event event) {

	}
}
