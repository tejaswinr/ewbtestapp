package com.ewb.config.client;

import java.util.concurrent.ExecutorService;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;

import com.ewb.kafkamessage.AbstractKafkaMessageListener;
import com.ewb.kafkamessage.KafkaMessage;
import com.ewb.kafkamessage.KafkaMessageVO;
import com.ewb.logger.CustomFileLogger;

public class KafkaProducerWorker extends AbstractKafkaMessageListener {

	private static final Logger LOGGER = CustomFileLogger.getInstance().getLogger(KafkaProducerWorker.class.getName());
	private final KafkaProducer<String, KafkaMessage> producer;

	public KafkaProducerWorker(ExecutorService kafkaMsgProcService, KafkaProducer<String, KafkaMessage> producer,
			int offerTimeout) {
		super(kafkaMsgProcService);
		this.producer = producer;
	}

	@Override
	protected void processKafkaMessage(KafkaMessageVO kafkaMessageVo) {
		if (kafkaMessageVo != null) {
			ProducerRecord<String, KafkaMessage> producerRecord = new ProducerRecord<String, KafkaMessage>(
					kafkaMessageVo.getKafkaTopic(), kafkaMessageVo.getKafkaMessage());
			LOGGER.debug("sending message: " + kafkaMessageVo);
			producer.send(producerRecord);
			LOGGER.debug("message sent");
		} else {
			LOGGER.info("Producer service is running");
		}
	}

}
