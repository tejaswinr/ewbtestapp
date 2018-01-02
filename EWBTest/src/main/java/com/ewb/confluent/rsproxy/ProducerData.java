package com.ewb.confluent.rsproxy;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProducerData {

	@JsonProperty(value = "key_schema_id")
	private final int keySchemaId;
	@JsonProperty(value = "value_schema_id")
	private final int valueSchemaId;
	@JsonProperty(value = "records")
	private final List<Map<String, Object>> records;

	public ProducerData(int keySchemaId, int valueSchemaId, List<Map<String, Object>> records) {
		super();
		this.keySchemaId = keySchemaId;
		this.valueSchemaId = valueSchemaId;
		this.records = records;
	}

}
