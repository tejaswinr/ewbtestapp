package com.ewb.confluent.rsproxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData.Record;
import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;

public class RestClient {

	public static final String BASE_URL = "http://192.168.217.132:8082/";
	public static final String SCHEMA_REGISTRY_URL = "http://192.168.217.132:8081";
	public static final String MEDIA_TYPE = "application/vnd.kafka.avro.v2+json";

	public static SchemaRegistryClient schemaRegistryClient = new CachedSchemaRegistryClient(SCHEMA_REGISTRY_URL, 100);

	public static void main(String[] args) throws IOException, RestClientException {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		postRequest(client);
		getRequest(client);
	}

	public static void getRequest(Client client) throws IOException, RestClientException {
		WebTarget target = client.target(UriBuilder.fromUri(BASE_URL));
		System.out.println("Consumer consuming data from Kafka REST proxy...");
		System.out.println(target.path("consumers/my_avro_consumer/instances/my_consumer_instance/records").request()
				.accept(MEDIA_TYPE).get(String.class));
	}

	public static void postRequest(Client client) throws IOException, RestClientException {
		client.register(JacksonJaxbJsonProvider.class);
		printSchemaRecords();
		for (int count = 1; count <= 10; count++) {
			ProducerData producerData = createProducerData(count);
			System.out.println("Producer posting data on Kafka REST proxy...");
			Entity<String> entity = Entity.entity(printJson(producerData), MEDIA_TYPE);
			WebTarget target = client.target(UriBuilder.fromUri(BASE_URL));
			Response resp = target.path("topics/jsontest1").request().accept("application/vnd.kafka.v2+json")
					.post(entity);
			System.out.println(resp.getStatus());
		}
	}

	public static String printJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(object));
		return mapper.writeValueAsString(object);
	}

	private static ProducerData createProducerData(Integer count) throws IOException, RestClientException {
		final Map<String, Object> valueMap = new HashMap<>();
		valueMap.put("name", "testUser-" + count);

		final Map<String, Object> recordKeyValue = new HashMap<>();
		recordKeyValue.put("key", count);
		recordKeyValue.put("value", valueMap);

		List<Map<String, Object>> kk = new ArrayList<Map<String, Object>>();
		kk.add(recordKeyValue);
		return new ProducerData(2, 1, kk);
	}

	private static void printSchemaRecords() throws IOException, RestClientException {
		System.out.println(schemaRegistryClient.getAllSubjects());
		System.out.println(schemaRegistryClient.getBySubjectAndID("jsontest1-value", 1));
		Schema schema = schemaRegistryClient.getBySubjectAndID("jsontest1-value", 1);
		Record record = new Record(schema);
		record.put("name", "testUser1");
		System.out.println(record);
	}
}
