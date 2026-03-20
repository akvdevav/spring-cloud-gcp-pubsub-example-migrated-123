package br.com.quintoandar.springcloudgcppubsubexample.publishers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

public abstract class PubSubPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    protected PubSubPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    protected abstract String queueName();

    public void publish(String message) {
        LOGGER.info("publishing to queue [{}], message: [{}]", queueName(), message);
        rabbitTemplate.convertAndSend(queueName(), message);
    }
}