package dev.geunho.mq_kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.KafkaContainer;

public class KafkaTransactionalTest {
  private static KafkaContainer kafka = new KafkaContainer();
  private final static String topic = "TRANSACTIONAL_TEST";

  private static TransactionalProducer producer;

  @BeforeAll
  public static void setUp() {
    kafka.start();
    String servers = kafka.getBootstrapServers();
    producer = new TransactionalProducer(servers, topic);
  }

  @Test
  public void sendTest() {
    producer.send("send me");
  }
}
