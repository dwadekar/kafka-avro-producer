package com.practice.kafka.kafkaavroproducer;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaAvroProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaAvroProducerApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(KafkaAdmin admin) {
		return args -> {
			try (AdminClient client = AdminClient.create(admin.getConfig())) {
				while (true) {
					Map<String, ConsumerGroupDescription> map =
							client.describeConsumerGroups(Collections.singletonList("so56134056")).all().get(10, TimeUnit.SECONDS);
					//Map<String, TopicDescription> map = client.describeTopics(Collections.singletonList("user-test-topic")).all().get(10, TimeUnit.SECONDS);
					//ListTopicsResult map = client.listTopics();

					System.out.println(map);
					System.in.read();
				}
			}
		};
	}

}
