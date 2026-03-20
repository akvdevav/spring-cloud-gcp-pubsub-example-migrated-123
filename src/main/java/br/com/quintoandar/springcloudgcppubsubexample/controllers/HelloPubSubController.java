package br.com.quintoandar.springcloudgcppubsubexample.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloPubSubController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloPubSubController.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public HelloPubSubController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/publish")
    public void publish(@RequestBody String message) {
        LOGGER.info("received a POST at /hello/publish with message=[{}]", message);
        rabbitTemplate.convertAndSend("hello.exchange", "hello.routing.key", message);
    }

}