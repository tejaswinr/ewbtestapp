package com.ewb.confluent.rsproxy;

public class Field {

	private final String name;
	private final String type;

	public Field(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

}
