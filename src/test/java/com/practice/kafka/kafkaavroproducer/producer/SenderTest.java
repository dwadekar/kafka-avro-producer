package com.practice.kafka.kafkaavroproducer.producer;

import com.practice.kafka.User;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SenderTest {
    @Mock
    private KafkaTemplate<String, User> kafkaTemplate;

    @InjectMocks
    private Sender sender;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSend() throws InterruptedException, ExecutionException {
        User user = User.newBuilder().setName("Dhananjay W").setFavoriteNumber(11).setFavoriteColor("White").build();
        SettableListenableFuture future = new SettableListenableFuture();
        ProducerRecord<String, User> producerRecord = new ProducerRecord("topic-name", null, user);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("topic-name", 1), 1, 1, 342, System.currentTimeMillis(), 1, 2);
        SendResult<String, User> sendResult = new SendResult<>(producerRecord, recordMetadata);
        future.set(sendResult);
        when(kafkaTemplate.send(any(), any(User.class))).thenReturn(future);
        ListenableFuture<SendResult<String, User>> listenableFuture = sender.send(user);

        SendResult<String, User> result = listenableFuture.get();
        assertEquals(result.getRecordMetadata().partition(), 1);
    }

    @Test(expected = ExecutionException.class)
    public void testSendFailure() throws InterruptedException, ExecutionException {
        User user = User.newBuilder().setName("Dhananjay W").setFavoriteNumber(11).setFavoriteColor("White").build();
        SettableListenableFuture future = new SettableListenableFuture();
        future.setException(new RuntimeException("Exception occurred...."));
        when(kafkaTemplate.send(any(), any(User.class))).thenReturn(future);
        ListenableFuture<SendResult<String, User>> listenableFuture = sender.send(user);
        listenableFuture.get();
    }

}
