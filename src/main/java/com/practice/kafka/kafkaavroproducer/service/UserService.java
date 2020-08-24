package com.practice.kafka.kafkaavroproducer.service;

import com.practice.kafka.User;
import com.practice.kafka.kafkaavroproducer.model.UserInfo;
import com.practice.kafka.kafkaavroproducer.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private Sender sender;

    public void createUser(UserInfo userInfo) {
        sender.send(User.newBuilder()
                .setName(userInfo.getName())
                .setFavoriteNumber(userInfo.getFavorite_number())
                .setFavoriteColor(userInfo.getFavorite_color())
                .build());
    }

}
