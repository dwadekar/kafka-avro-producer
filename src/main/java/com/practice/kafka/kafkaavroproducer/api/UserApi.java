package com.practice.kafka.kafkaavroproducer.api;

import com.practice.kafka.kafkaavroproducer.model.UserInfo;
import com.practice.kafka.kafkaavroproducer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
public class UserApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public void createUser(@RequestBody UserInfo userInfo) {
        userService.createUser(userInfo);
        LOGGER.info("User Created successfully!!");
    }
}
