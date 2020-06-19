package com.springboot.config;

import com.ballaci.model.Event;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAvroProducerConfig {

	@Value("${kafka.boot.server}")
	private String kafkaServer;

	@Value("${kafka.schema.registry.url}")
	private String schemaRegistryUrl;

	@Bean
	public KafkaTemplate<String, Event> kafkaAvroTemplate() {
		return new KafkaTemplate<>(avroProducerConfig());
	}

	@Bean
	public ProducerFactory<String, Event> avroProducerConfig() {
		// TODO Auto-generated method stub
		System.out.println("Starting producer config with server url: " + kafkaServer);
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		config.put("schema.registry.url", schemaRegistryUrl);
		//Uncomment the below if you want to send String instead of an Object through Kafka
		//config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
	}
}
