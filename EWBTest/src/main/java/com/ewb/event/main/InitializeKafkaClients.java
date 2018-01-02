package com.ewb.event.main;

import java.util.List;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;

import com.ewb.config.client.CacheEventTypeKafkaClientMap;
import com.ewb.config.client.KafkaClientConfig;
import com.ewb.config.client.KafkaClientFactory;
import com.ewb.event.adapter.EWBKafkaConsumer;
import com.ewb.event.adapter.EWBKafkaProducer;

public class InitializeKafkaClients {

	private List<KafkaClientConfig> clientConfigs;
	private KafkaClientFactory clientFactory;
	private CacheEventTypeKafkaClientMap cache;

	public InitializeKafkaClients(String systemId) {
		super();
	}

	public void initProducerThread(EWBKafkaProducer producer) {

	}

	public void initConsumerThread(EWBKafkaConsumer consumer) {

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
