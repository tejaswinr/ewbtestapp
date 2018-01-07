package com.ewb.config.client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;

import com.ewb.kafkamessage.KafkaMessage;
import com.ewb.kafkamessage.KafkaMessageVO;
import com.ewb.logger.CustomFileLogger;

public class KafkaProducerWorker implements Runnable {

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(KafkaProducerWorker.class.getName());
	private final KafkaProducer<String, KafkaMessage> producer;
	private boolean keepRunning;
	private int offerTimeout;
	private BlockingQueue<KafkaMessageVO> producerQueue;

	public KafkaProducerWorker(KafkaProducer<String, KafkaMessage> producer, BlockingQueue<KafkaMessageVO> producerQueue,
			int offerTimeout) {
		super();
		this.producer = producer;
		this.producerQueue = producerQueue;
		this.keepRunning = true;
		this.offerTimeout = offerTimeout;
	}

	@Override
	public void run() {
		LOGGER.debug("Starting kafka producer..");
		if (producer == null) {
			LOGGER.error("Producer is null.. returning");
			return;
		}
		try {
			if (producer != null) {
				while (true) {
					KafkaMessageVO messageVo = producerQueue.poll(offerTimeout, TimeUnit.SECONDS);
					if (messageVo != null) {
						ProducerRecord<String, KafkaMessage> producerRecord = new ProducerRecord<String, KafkaMessage>(
								messageVo.getKafkaTopic(), messageVo.getKafkaMessage());
						LOGGER.debug("sending message: " + messageVo);
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

	public void setPollTimeoutInMs(int pollTimeoutInMs) {
		this.offerTimeout = pollTimeoutInMs;
	}

}
