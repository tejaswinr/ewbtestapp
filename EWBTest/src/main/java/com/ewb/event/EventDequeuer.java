package com.ewb.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractDequeuer;
import com.ewb.common.Listener;

public class EventDequeuer extends AbstractDequeuer<Event> implements Runnable {

	private List<Listener<Event>> eventListeners;
	private boolean keepRunning;

	public EventDequeuer(BlockingQueue<Event> outboundQueue, int pollTimeout) {
		super(outboundQueue, pollTimeout);
		this.keepRunning = true;
	}

	public EventDequeuer(BlockingQueue<Event> outboundQueue) {
		super(outboundQueue);
		this.keepRunning = true;
	}

	private void updateEventListeners(Event event) {
		if (eventListeners != null) {
			for (Listener<Event> eventListener : eventListeners) {
				eventListener.updateListener(event);
			}
		}
	}

	public void registerEventListener(Listener<Event> listener) {
		if (eventListeners == null) {
			eventListeners = new ArrayList<>();
		}
		eventListeners.add(listener);
	}

	@Override
	public void run() {
		// TODO
		while (keepRunning) {
			try {
				Event event = dequeue();
				if (event != null) {
					updateEventListeners(event);
				}
			} catch (InterruptedException e) {
				// LOGGER
				e.printStackTrace();
				keepRunning = false;
			}
		}
	}

	@Override
	protected void executeOnPollTimeOut() {
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
