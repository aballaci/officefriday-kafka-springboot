package com.springboot.service;

import com.springboot.model.Mitarbeiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaReciever {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReciever.class);

	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group.id}")
	public void recieveData(Mitarbeiter mitarbeiter) {
		LOGGER.info("Data - " + mitarbeiter.toString() + " recieved");
	}
}
