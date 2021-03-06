package dev.geunho.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringKafkaApplication implements CommandLineRunner {

    public static Logger logger = LoggerFactory.getLogger(SpringKafkaApplication.class);
    private static final String topic = "spring-kafka-test";

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args).close();
    }

    @Autowired
    private KafkaTemplate<String, String> template;

    private final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public void run(String... args) throws Exception {
        this.template.send(topic,"abc", "foo1");
        this.template.send(topic, "bbc","foo2");
        this.template.send(topic, "ccc","foo3");
        latch.await(60, TimeUnit.SECONDS);
        logger.info("All received");
    }

    @KafkaListener(topics = topic)
    public void listen(ConsumerRecord<?, ?> cr) {
        logger.info(cr.toString());
        latch.countDown();
    }
}
