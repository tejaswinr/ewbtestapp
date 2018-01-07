package com.ewb.event;

import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractEnqueuer;

public class EventEnqueuer extends AbstractEnqueuer<Event> {

	public EventEnqueuer(BlockingQueue<Event> inboundQueue, int offerTimeout) {
		super(inboundQueue, offerTimeout);
	}

	public EventEnqueuer(BlockingQueue<Event> inboundQueue) {
		super(inboundQueue);
	}

	@Override
	public boolean enqueue(Event event) {
		try {
			return super.enqueue(event);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
