package com.ewb.event.main;

import java.util.List;

import com.ewb.config.client.KafkaClientConfig;
import com.ewb.config.dao.KafkaClientConfigDao;

public class InitApplication {
	private final String systemId;
	private KafkaClientConfigDao clientConfigDao;
	private InitializeKafkaClients initKafkaClients;
	private InitEventRouterService initMessageRouterService;

	public InitApplication(String systemId, KafkaClientConfigDao clientConfigDao) {
		super();
		this.systemId = systemId;
		this.clientConfigDao = clientConfigDao;
	}

	public void initializeKafkaClients(List<KafkaClientConfig> clientConfigs) {

	}

	public void initializeMessageRouter(List<KafkaClientConfig> clientConfigs) {

	}

	public void initializeMessageRouterServices() {

	}
}
