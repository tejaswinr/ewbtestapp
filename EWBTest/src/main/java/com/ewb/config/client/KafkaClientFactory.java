package com.ewb.config.client;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;

import com.ewb.config.init.ConfigConstants;
import com.ewb.config.init.ConfigFileLoader;
import com.ewb.kafkamessage.KafkaMessage;
import com.ewb.kafkamessage.KafkaMessageSerDe;
import com.ewb.kafkamessage.KafkaMessageVO;
import com.ewb.logger.CustomFileLogger;

public class KafkaClientFactory {

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(KafkaClientFactory.class.getName());

	public static KafkaProducerWorker createProducer(KafkaClientConfig clientConfigs,
			BlockingQueue<KafkaMessageVO> producerQueue) {
		LOGGER.info("Creating producer client ");
		String topic = (String) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.EWBPRODUCERS_TOPICS);
		Integer offerTimeout = (Integer) ConfigFileLoader.CONFIG
				.getConfigPropertyValue(ConfigConstants.EWBPRODCUERS_OFFER_TIMEOUT);
		String host = (String) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.BROKER_HOST);
		Integer port = (Integer) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.BROKER_PORT);
		String clientId = (String) ConfigFileLoader.CONFIG
				.getConfigPropertyValue(ConfigConstants.EWBPRODUCERS_CLIENTID);
		KafkaProducer<String, KafkaMessage> producer = createKafkaProducer(host, port, clientId);
		// return new KafkaProducerWorker(producer, producerQueue,
		// offerTimeout);
		return null;
	}

	public static KafkaProducer<String, KafkaMessage> createKafkaProducer(String host, Integer port, String clientId) {
		Properties producerConfig = getKafkaProducerConfig(host, port, clientId);
		return new KafkaProducer<>(producerConfig);
	}

	private static Properties getKafkaProducerConfig(String host, Integer port, String clientId) {
		return getKafkaProducerConfig(host, port, clientId, StringSerializer.class.getName(),
				KafkaMessageSerDe.class.getName());
	}

	private static Properties getKafkaProducerConfig(String host, Integer port, String clientId, String keySerializer,
			String valueSerializer) {
		Properties props = new Properties();
		props.put("bootstrap.servers", host + ":" + port);
		props.put("key.serializer", keySerializer);
		props.put("value.serializer", valueSerializer);
		props.put("request.required.acks", "1");
		props.put("client.id", clientId);
		props.put("retry.backoff.ms", "1000");
		return props;
	}

	public static KafkaConsumerWorker createConsumer(KafkaClientConfig clientConfig,
			BlockingQueue<KafkaMessageVO> consumerQueue) {
		LOGGER.info("Creating consumer client");
		String topics = (String) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.EWBCONSUMERS_TOPICS);
		Long pollTimeout = (Long) ConfigFileLoader.CONFIG
				.getConfigPropertyValue(ConfigConstants.EWBCONSUMERS_POLL_TIMEOUT);
		String host = (String) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.BROKER_HOST);
		Integer port = (Integer) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.BROKER_PORT);
		String groupId = (String) ConfigFileLoader.CONFIG.getConfigPropertyValue(ConfigConstants.EWBCONSUMERS_GROUPID);
		Properties consumerConfig = getKafkaConsumerConfig(host, port, groupId);
		KafkaConsumer<String, KafkaMessage> consumer = new KafkaConsumer<>(consumerConfig);
		// return new KafkaConsumerWorker(consumer, consumerQueue,
		// Arrays.asList(topics.split(",")), pollTimeout);
		return null;
	}

	private static Properties getKafkaConsumerConfig(String host, Integer port, String groupId) {
		return getKafkaConsumerConfig(host, port, groupId, StringDeserializer.class.getName(),
				KafkaMessageSerDe.class.getName());
	}

	private static Properties getKafkaConsumerConfig(String host, Integer port, String groupId, String keyDeserializer,
			String valueDeserializer) {
		Properties props = new Properties();
		props.put("bootstrap.servers", host + ":" + port);
		props.put("group.id", groupId);
		props.put("key.deserializer", keyDeserializer);
		props.put("value.deserializer", valueDeserializer);
		return props;
	}

}
