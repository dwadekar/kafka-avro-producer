package com.practice.kafka.kafkaavroproducer.actuator;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

//@Component
public class KafkaHealthIndicator extends AbstractHealthIndicator {

    //@Value("${kafka.topic.avro]")
    private String kafkaTopicName;

    private KafkaAdmin kafkaAdmin;

    public KafkaHealthIndicator(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try (AdminClient client = AdminClient.create(kafkaAdmin.getConfig())) {
					/*Map<String, ConsumerGroupDescription> map =
							client.describeConsumerGroups(Collections.singletonList("so56134056")).all().get(10, TimeUnit.SECONDS);*/
                ListTopicsResult map = client.listTopics();

                System.out.println(map.namesToListings().get());
                System.in.read();
        }
    }
}
