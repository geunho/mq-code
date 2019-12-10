package dev.geunho.mq_kafka;

import org.junit.Rule;
import org.testcontainers.containers.KafkaContainer;

public class KafkaTransactionalTest {
  @Rule
  public KafkaContainer kafka = new KafkaContainer();

}
