package br.com.quintoandar.springcloudgcppubsubexample;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class SpringCloudGcpPubSubExampleApplication {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue", true);
    }

    @RabbitListener(queues = "myQueue")
    public void receiveMessage(Object message) {
        System.out.println("Received message: " + message);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGcpPubSubExampleApplication.class, args);
    }
}