package com.ewb.common;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDequeuer<T> implements Dequeuer<T> {

	private final BlockingQueue<T> outboundQueue;
	private int pollTimeout;

	public AbstractDequeuer(BlockingQueue<T> outboundQueue) {
		super();
		this.outboundQueue = outboundQueue;
	}

	public AbstractDequeuer(BlockingQueue<T> outboundQueue, int pollTimeout) {
		super();
		this.outboundQueue = outboundQueue;
		this.pollTimeout = pollTimeout;
	}

	public T dequeue() throws InterruptedException {
		if (outboundQueue == null) {
			return null;
		}
		return outboundQueue.poll(pollTimeout, TimeUnit.SECONDS);
	}

}
