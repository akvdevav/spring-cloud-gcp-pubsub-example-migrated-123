package br.com.quintoandar.springcloudgcppubsubexample.subscribers.config;

import br.com.quintoandar.springcloudgcppubsubexample.subscribers.consumers.HelloPubSubConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@EnableRabbit
public class HelloPubSubSubscriberConfig implements RabbitListenerConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloPubSubSubscriberConfig.class);

    private final RabbitTemplate rabbitTemplate;

    private final HelloPubSubConsumer helloPubSubConsumer;

    @Autowired
    public HelloPubSubSubscriberConfig(RabbitTemplate rabbitTemplate, HelloPubSubConsumer helloPubSubConsumer) {
        this.rabbitTemplate = rabbitTemplate;
        this.helloPubSubConsumer = helloPubSubConsumer;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        return factory;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "hello-topic", durable = "true"))
    public void listen(String message) {
        helloPubSubConsumer.consumer().accept(message);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        LOGGER.info("Subscribing {} to {}", helloPubSubConsumer.getClass().getSimpleName(),
                helloPubSubConsumer.subscription());
    }
}