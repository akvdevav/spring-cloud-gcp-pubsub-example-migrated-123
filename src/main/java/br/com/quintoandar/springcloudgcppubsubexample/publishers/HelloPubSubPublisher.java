package br.com.quintoandar.springcloudgcppubsubexample.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloPubSubPublisher {

    private final RabbitTemplate rabbitTemplate;

    public HelloPubSubPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(Object message) {
        rabbitTemplate.convertAndSend("hello-pubsub", message);
    }
}