package com.ewb.kafkamessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractDequeuer;
import com.ewb.common.Listener;

public class KafkaMessageDequeuer extends AbstractDequeuer<KafkaMessageVO> implements Runnable {

	private List<Listener<KafkaMessageVO>> kafkaMessageListeners;
	private boolean keepRunning;

	public KafkaMessageDequeuer(BlockingQueue<KafkaMessageVO> outboundQueue) {
		super(outboundQueue);
		this.keepRunning = true;
	}

	public KafkaMessageDequeuer(BlockingQueue<KafkaMessageVO> outboundQueue, int pollTimeout) {
		super(outboundQueue, pollTimeout);
		this.keepRunning = true;
	}

	private void updateKafkaMessageListeners(KafkaMessageVO kafkaMessage) {
		if (kafkaMessageListeners != null) {
			for (Listener<KafkaMessageVO> kafkaMessageListener : kafkaMessageListeners) {
				kafkaMessageListener.updateListener(kafkaMessage);
			}
		}
	}

	public void registerEventListener(Listener<KafkaMessageVO> listener) {
		if (kafkaMessageListeners == null) {
			kafkaMessageListeners = new ArrayList<>();
		}
		kafkaMessageListeners.add(listener);
	}

	@Override
	public void run() {
		// TODO
		while (keepRunning) {
			try {
				KafkaMessageVO message = dequeue();
				if (message != null) {
					updateKafkaMessageListeners(message);
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
