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

	protected abstract void executeOnPollTimeOut();

	public abstract boolean keepRunning();

	public T dequeue() throws InterruptedException {
		if (outboundQueue == null) {
			// TODO throw exception
			return null;
		}
		while (keepRunning()) {
			T obj = outboundQueue.poll(pollTimeout, TimeUnit.SECONDS);
			if (obj == null) {
				executeOnPollTimeOut();
				continue;
			}
			return obj;
		}
		return null;
	}

}
