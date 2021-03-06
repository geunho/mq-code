package dev.geunho.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SpringCloudStreamsKafkaApplication {
    private static final Logger logger = LoggerFactory.getLogger(SpringCloudStreamsKafkaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamsKafkaApplication.class, args);
    }

    @Bean
    public Supplier<Message<String>> send(String message) {
        return () -> {
            logger.info("Sending: " + message);
            return MessageBuilder.withPayload(message)
                    .setHeader("partitionKey", message.length())
                    .build();
        };
    }

    @Bean
    public Consumer<Message<String>> listen() {
        return message -> {
            logger.info("Sending: " + message);
        };
    }
}
