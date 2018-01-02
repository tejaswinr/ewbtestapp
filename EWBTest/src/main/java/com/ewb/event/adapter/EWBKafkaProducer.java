package com.ewb.event.adapter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;

import com.ewb.event.entity.KafkaMessage;
import com.ewb.event.logger.CustomFileLogger;

public class EWBKafkaProducer implements Runnable {

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(EWBKafkaProducer.class.getName());
	private final String topic;
	private final KafkaProducer<String, KafkaMessage> producer;
	private boolean keepRunning;
	private long offerTimeout;
	private BlockingQueue<KafkaMessage> producerQueue;

	public EWBKafkaProducer(KafkaProducer<String, KafkaMessage> producer, String topic, int offerTimeout) {
		super();
		this.topic = topic;
		this.producer = producer;
		this.keepRunning = true;
		this.offerTimeout = offerTimeout;
	}

	@Override
	public void run() {
		LOGGER.debug("Starting kafka producer for topic=" + topic);
		if (producer == null) {
			LOGGER.error("Producer is null.. returning");
			return;
		}
		try {
			if (producer != null) {
				while (true) {
					KafkaMessage message = producerQueue.poll(offerTimeout, TimeUnit.SECONDS);
					if (message != null) {
						ProducerRecord<String, KafkaMessage> producerRecord = new ProducerRecord<String, KafkaMessage>(
								topic, message);
						LOGGER.debug("sending message: " + message);
						producer.send(producerRecord);
						LOGGER.debug("message sent");
					} else {
						LOGGER.info("Producer service is running");
					}
				}
			}
		} catch (InterruptedException exp) {
			LOGGER.error("Producer service interrupted.", exp);
		}
	}

	public boolean isKeepRunning() {
		return keepRunning;
	}

	public void setKeepRunning(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}

	public long getPollTimeoutInMs() {
		return offerTimeout;
	}

	public void setPollTimeoutInMs(long pollTimeoutInMs) {
		this.offerTimeout = pollTimeoutInMs;
	}

}
