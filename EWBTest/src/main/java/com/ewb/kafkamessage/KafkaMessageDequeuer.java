package com.ewb.kafkamessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.ewb.common.AbstractDequeuer;
import com.ewb.common.Listener;

public class KafkaMessageDequeuer extends AbstractDequeuer<KafkaMessage> implements Runnable {

	private List<Listener<KafkaMessage>> kafkaMessageListeners;
	private boolean keepRunning;

	public KafkaMessageDequeuer(BlockingQueue<KafkaMessage> outboundQueue) {
		super(outboundQueue);
		this.keepRunning = true;
	}

	public KafkaMessageDequeuer(BlockingQueue<KafkaMessage> outboundQueue, int pollTimeout) {
		super(outboundQueue, pollTimeout);
		this.keepRunning = true;
	}

	private void updateKafkaMessageListeners(KafkaMessage kafkaMessage) {
		if (kafkaMessageListeners != null) {
			for (Listener<KafkaMessage> kafkaMessageListener : kafkaMessageListeners) {
				kafkaMessageListener.updateListener(kafkaMessage);
			}
		}
	}

	public void registerEventListener(Listener<KafkaMessage> listener) {
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
				KafkaMessage message = dequeue();
				if (message == null) {
					continue;
				}
				updateKafkaMessageListeners(message);
			} catch (InterruptedException e) {
				// LOGGER
				e.printStackTrace();
				keepRunning = false;
			}
		}
	}

	public boolean isKeepRunning() {
		return keepRunning;
	}

	public void setKeepRunning(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}

}
