package br.com.quintoandar.springcloudgcppubsubexample.subscribers.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PubSubConsumer {

    protected final RabbitTemplate rabbitTemplate;

    public PubSubConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Name of an existing queue.
     */
    public String queueName() {
        return "${queue.name}";
    }

    /**
     * The actual consumer logic.
     *
     * @param message The message received from the queue.
     */
    protected void consume(String message) {
        // Default implementation - can be overridden
    }

    /**
     * Implementation of a {@link org.springframework.amqp.core.MessageListener} which only calls the
     * {@link #consume(String) consume} method.
     */
    @RabbitListener(queuesToDeclare = @org.springframework.amqp.rabbit.annotation.Queue(name = "${queue.name}", durable = "true"))
    public void listen(String message) {
        consume(message);
    }
}