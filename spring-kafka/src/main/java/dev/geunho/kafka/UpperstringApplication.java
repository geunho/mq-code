package dev.geunho.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableBinding(Processor.class)
@PropertySource("classpath:upperstring.yml")
public class UpperstringApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpperstringApplication.class, args);
    }

    @StreamListener("listen-in-0")
    @SendTo("generate-out-0")
    public String handle(String value) {
        System.out.println("Received: " + value);
        return value.toUpperCase();
    }
}
