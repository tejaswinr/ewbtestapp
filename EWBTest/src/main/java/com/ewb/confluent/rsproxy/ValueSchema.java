package com.ewb.confluent.rsproxy;

import java.util.List;

public class ValueSchema {

	private final String type;
	private final String name;
	private final List<Field> fields;

	public ValueSchema(String type, String name, List<Field> fields) {
		super();
		this.type = type;
		this.name = name;
		this.fields = fields;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public List<Field> getFields() {
		return fields;
	}

}
