package com.springboot.service;

import com.ballaci.model.Event;
import com.ballaci.model.EventType;
import com.github.javafaker.Faker;
import com.springboot.model.Mitarbeiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class KafkaAvroSender implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaAvroSender.class);

    @Autowired
    private KafkaTemplate<String, Event> kafkaAvroTemplate;

    @Value("${kafka.avro.topic.name}")
    private String topicName;

    @Value("${kafka.avro.numRecords}")
    private int numRecords;

    static Faker faker = new Faker(new Locale("de"));


    public void sendData(Event event) {
        // TODO Auto-generated method stub
        Map<String, Object> headers = new HashMap<>();
        headers.put(KafkaHeaders.TOPIC, topicName);
        headers.put(KafkaHeaders.MESSAGE_KEY, event.getId());
        kafkaAvroTemplate.send(new GenericMessage<Event>(event, headers));
        // use the below to send String values through kafka
        // kafkaTemplate.send(topicName, "some string value")
        LOGGER.info("Data - " + event.toString() + " sent to Kafka Topic - " + topicName);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Started sending events");
        String[] types = new String[]{"type1", "type2"};
        String ts = String.valueOf(ZonedDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli());

        for (int i = 0; i < numRecords; i++) {
            String id = String.valueOf(i);
            Event event = new Event(id, faker.options().nextElement(types), ts);
            sendData(event);
            Thread.sleep(2000);
        }

    }
}
