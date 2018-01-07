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

	public boolean enqueue(T object) throws InterruptedException {
		return inboundQueue.offer(object, offerTimeout, TimeUnit.SECONDS);
	}

}
