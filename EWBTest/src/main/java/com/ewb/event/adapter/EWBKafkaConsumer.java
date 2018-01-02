package com.ewb.event.adapter;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;

import com.ewb.event.entity.KafkaMessage;
import com.ewb.event.logger.CustomFileLogger;

public class EWBKafkaConsumer implements Runnable {

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(EWBKafkaConsumer.class.getName());
	private final List<String> topics;
	private final KafkaConsumer<String, KafkaMessage> consumer;
	private boolean keepRunning;
	private BlockingQueue<KafkaMessage> consumerQueue;
	private long pollTimeoutInMs;

	public EWBKafkaConsumer(KafkaConsumer<String, KafkaMessage> consumer, List<String> topics, long pollTimeoutInMs) {
		super();
		this.topics = topics;
		this.consumer = consumer;
		this.keepRunning = true;
		this.pollTimeoutInMs = pollTimeoutInMs;
	}

	@Override
	public void run() {
		LOGGER.debug("Starting kafka consumer for topics=" + topics);
		if (consumer == null) {
			LOGGER.error("Consumer config is null.. returning");
			return;
		}
		consumer.subscribe(topics);
		while (keepRunning) {
			ConsumerRecords<String, KafkaMessage> records = consumer.poll(pollTimeoutInMs);
			if (records.isEmpty()) {
				LOGGER.debug("Kafka consumer service is running");
			}
			for (ConsumerRecord<String, KafkaMessage> record : records) {
				LOGGER.debug("offset =" + record.offset() + ", key =" + record.key() + ", value = " + record.value());
				consumerQueue.offer(record.value());
			}
		}
		consumer.close();
	}

	public boolean isKeepRunning() {
		return keepRunning;
	}

	public void setKeepRunning(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}

	public long getPollTimeoutInMs() {
		return pollTimeoutInMs;
	}

	public void setPollTimeoutInMs(long pollTimeoutInMs) {
		this.pollTimeoutInMs = pollTimeoutInMs;
	}

}
