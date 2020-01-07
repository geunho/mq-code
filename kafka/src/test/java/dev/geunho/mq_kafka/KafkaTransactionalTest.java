package dev.geunho.mq_kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.KafkaContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KafkaTransactionalTest {
  private static KafkaContainer kafka = new KafkaContainer("4.1.2"); // kafka 1.1.1
  private final static String topic = "TRANSACTIONAL_TEST";

  private static TransactionalProducer producer;

  @BeforeAll
  public static void setUp() {
    kafka.start();
    String servers = kafka.getBootstrapServers();
    Map options = new HashMap();
    options.put("max.request.size", 5 * 1024 * 1024);

    producer = new TransactionalProducer(servers, topic, options);
  }

  @Test
  public void sendTest() {
    producer.send("send me");
  }

  @Test
  public void sendLargeMessageTest() {
    char[] chars = new char[4*1024*1024]; //4mb

    Arrays.fill(chars, 'a');
    String message = new String(chars);
    producer.send(message);
  }
}
