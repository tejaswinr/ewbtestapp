package com.ewb.config.init;

@SuppressWarnings("rawtypes")
public enum ConfigConstants {

	CONFIG_FILE_PROPERTY("ewb.client.config.location", null, false, String.class),
	BROKER_HOST("ewb.broker.hosts", null, true, String.class),
	BROKER_PORT("ewb.broker.ports", null, true, Integer.class),
	EWBPRODUCERS_TOPICS("ewb.producers.topics", null, true, String.class),
	EWBPRODUCERS_CLIENTID("ewb.producers.clientid", "mail-producer", false, String.class),
	EWBPRODCUERS_OFFER_TIMEOUT("ewb.producers.offer.timeout.sec", "60", false, Integer.class),
	EWBCONSUMERS_TOPICS("ewb.consumers.topics", null, true, String.class),
	EWBCONSUMERS_GROUPID("ewb.consumers.groupid", "email-consumers", false, String.class),
	EWBCONSUMERS_PERTOPIC("ewb.consumers.pertopic", "1", false, Integer.class),
	EWBCONSUMERS_POLL_TIMEOUT("ewb.consumers.poll.timeout", "100000", false, Long.class);

	private final String configProperty;
	private final String defaultValue;
	private final boolean isMandatory;
	private final Class valueClass;

	ConfigConstants(String configProperty, String defaultValue, boolean isMandatory, Class valueClass) {
		this.configProperty = configProperty;
		this.defaultValue = defaultValue;
		this.isMandatory = isMandatory;
		this.valueClass = valueClass;
	}

	public String getConfigProperty() {
		return configProperty;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public Class getValueClass() {
		return valueClass;
	}
}
