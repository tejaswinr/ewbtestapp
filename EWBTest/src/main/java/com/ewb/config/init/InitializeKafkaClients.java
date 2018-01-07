package com.ewb.config.init;

import java.util.List;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;

import com.ewb.config.client.EventTypeCategoryCache;
import com.ewb.config.client.KafkaConsumerWorker;
import com.ewb.config.client.KafkaProducerWorker;
import com.ewb.config.client.KafkaClientConfig;
import com.ewb.config.client.KafkaClientFactory;

public class InitializeKafkaClients {

	private List<KafkaClientConfig> clientConfigs;
	private KafkaClientFactory clientFactory;
	private EventTypeCategoryCache cache;

	public InitializeKafkaClients(String systemId) {
		super();
	}

	public void initProducerThread(KafkaProducerWorker producer) {

	}

	public void initConsumerThread(KafkaConsumerWorker consumer) {

	}

	public void run() {
		initKafkaClientConfigs();
	}

	public void initKafkaClientConfigs() {
		/*
		 * for (KafkaClientConfig clientConfig : clientConfigs) {
		 * if (clientConfig == KafkaClientType.PRODUCER) {
		 * initProducerThread(clientFactory.createProducer(clientConfig));
		 * } else {
		 * initConsumerThread(clientFactory.createConsumer(clientConfig));
		 * }
		 * }
		 */
	}

	public void updateCache(Producer producer, KafkaClientConfig clientConfig) {

	}

	public void updateCache(Consumer consumer, KafkaClientConfig clientConfig) {

	}

}
