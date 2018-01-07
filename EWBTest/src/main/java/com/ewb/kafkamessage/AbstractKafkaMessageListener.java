package com.ewb.kafkamessage;

import java.util.concurrent.ExecutorService;

import com.ewb.common.Listener;

public abstract class AbstractKafkaMessageListener implements Listener<KafkaMessage> {

	private final ExecutorService kafkaMsgProcService;

	public AbstractKafkaMessageListener(ExecutorService kafkaMsgProcService) {
		super();
		this.kafkaMsgProcService = kafkaMsgProcService;
	}

	@Override
	public final void updateListener(KafkaMessage kafkaMessage) {
		if (kafkaMsgProcService != null) {
			kafkaMsgProcService.submit(() -> processKafkaMessage(kafkaMessage));
		} else {
			// TODO
		}
	}

	protected abstract void processKafkaMessage(KafkaMessage kafkaMessage);

}
