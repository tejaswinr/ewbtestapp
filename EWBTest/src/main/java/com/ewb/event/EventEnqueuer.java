package com.ewb.event;

import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractEnqueuer;

public class EventEnqueuer extends AbstractEnqueuer<Event> {

	private boolean keepRunning;

	public EventEnqueuer(BlockingQueue<Event> inboundQueue, int offerTimeout) {
		super(inboundQueue, offerTimeout);
		this.keepRunning = true;
	}

	public EventEnqueuer(BlockingQueue<Event> inboundQueue) {
		super(inboundQueue);
		this.keepRunning = true;
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

	@Override
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
