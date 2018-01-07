package com.ewb.kafkamessage;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaMessageSerDe implements Serializer<KafkaMessage>, Deserializer<KafkaMessage> {
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// do nothing

	}

	@Override
	public byte[] serialize(String topic, KafkaMessage data) {
		try {
			if (data == null)
				return null;
			else
				return mapper.writeValueAsBytes(data);
		} catch (JsonProcessingException exp) {
			throw new SerializationException("Error when serializing kafka message to byte[]: " + exp.getMessage());
		}
	}

	@Override
	public void close() {
		// do nothing

	}

	@Override
	public KafkaMessage deserialize(String topic, byte[] data) {
		try {
			return mapper.readValue(data, KafkaMessage.class);
		} catch (IOException exp) {
			throw new SerializationException("Error when deserializing byte[] to  kafka message: " + exp.getMessage());
		}
	}
}
