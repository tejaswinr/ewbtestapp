package com.ewb.config.client;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;

import com.ewb.common.Enqueuer;
import com.ewb.kafkamessage.KafkaMessage;
import com.ewb.kafkamessage.KafkaMessageVO;
import com.ewb.logger.CustomFileLogger;

public class KafkaConsumerWorker implements Runnable {

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(KafkaConsumerWorker.class.getName());
	private final KafkaConsumer<String, KafkaMessage> consumer;
	private final Enqueuer<KafkaMessageVO> kafkaMessageEnqueuer;
	private final List<String> topics;
	private boolean keepRunning;
	private long pollTimeoutInMs;

	public KafkaConsumerWorker(KafkaConsumer<String, KafkaMessage> consumer,
			Enqueuer<KafkaMessageVO> kafkaMessageEnqueuer, List<String> topics, long pollTimeoutInMs) {
		super();
		this.topics = topics;
		this.consumer = consumer;
		this.kafkaMessageEnqueuer = kafkaMessageEnqueuer;
		this.keepRunning = true;
		this.pollTimeoutInMs = pollTimeoutInMs;
	}

	@Override
	public void run() {
		LOGGER.debug("Starting kafka consumer for topics={}", topics);
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
				try {
					kafkaMessageEnqueuer.enqueue(new KafkaMessageVO(record.value(), record.topic()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					keepRunning = false;
				}
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
