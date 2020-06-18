package com.springboot.service;

import java.util.HashMap;
import java.util.Map;

import com.springboot.model.Mitarbeiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.topic.name}")
	private String topicName;

	public void sendData(Mitarbeiter mitarbeiter) {
		// TODO Auto-generated method stub
		Map<String, Object> headers = new HashMap<>();
		headers.put(KafkaHeaders.TOPIC, topicName);
		headers.put(KafkaHeaders.MESSAGE_KEY, mitarbeiter.getUserId());
		kafkaTemplate.send(new GenericMessage<Mitarbeiter>(mitarbeiter, headers));
		// use the below to send String values through kafka
		// kafkaTemplate.send(topicName, "some string value")
		LOGGER.info("Data - " + mitarbeiter.toString() + " sent to Kafka Topic - " + topicName);
	}
}
