package com.ewb.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractEnqueuer<T> implements Enqueuer<T> {

	private final BlockingQueue<T> inboundQueue;
	private int offerTimeout;

	public AbstractEnqueuer(BlockingQueue<T> inboundQueue) {
		super();
		this.inboundQueue = inboundQueue;
	}

	public AbstractEnqueuer(BlockingQueue<T> inboundQueue, int offerTimeout) {
		super();
		this.inboundQueue = inboundQueue;
		this.offerTimeout = offerTimeout;
	}

	protected abstract void executeOnOfferTimeOut();

	public abstract boolean keepRunning();

	public boolean enqueue(T object) throws InterruptedException {
		if (inboundQueue == null) {
			// TODO throw exception
			return false;
		}
		while (keepRunning()) {
			boolean status = inboundQueue.offer(object, offerTimeout, TimeUnit.SECONDS);
			if (!status) {
				executeOnOfferTimeOut();
				continue;
			}
			return status;
		}
		return false;
	}

}
