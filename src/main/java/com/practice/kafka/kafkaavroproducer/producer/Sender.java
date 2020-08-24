package com.practice.kafka.kafkaavroproducer.producer;

import com.practice.kafka.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topic.avro}")
    private String avroTopic;

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public ListenableFuture<SendResult<String, User>> send(User user) {
        LOGGER.info("sending user='{}'", user.toString());
        //kafkaTemplate.send(avroTopic, user);
        return kafkaTemplate.send(avroTopic, user);
    }
}
