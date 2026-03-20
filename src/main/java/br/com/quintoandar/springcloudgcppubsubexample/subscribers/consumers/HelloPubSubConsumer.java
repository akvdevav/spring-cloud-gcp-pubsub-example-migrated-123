package br.com.quintoandar.springcloudgcppubsubexample.subscribers.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class HelloPubSubConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloPubSubConsumer.class);
    private final RabbitTemplate rabbitTemplate;

    public HelloPubSubConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "hello-rabbitmq-queue", durable = "true"))
    public void consume(String message) {
        LOGGER.info("message received: " + message);
        // Process message here
        // Acknowledgment is handled automatically by RabbitMQ
    }
}