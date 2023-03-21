package com.hvn.springkafka.controller;

import com.hvn.springkafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;

    public static final String TOPIC = "Kafka_Example_json";

    @GetMapping("/publish/{message}")
    public String post(@PathVariable("message") final String name) {

        kafkaTemplate.send(TOPIC,new User(name, "Technology", 1200L));
        return "Publish successfully";
    }
}
