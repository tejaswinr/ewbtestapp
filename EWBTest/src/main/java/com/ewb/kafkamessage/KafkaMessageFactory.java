package com.ewb.kafkamessage;

import com.ewb.event.Event;

public interface KafkaMessageFactory {

	public default KafkaMessage createKafkaMessage(Event event) {
		return null;
	}

}
