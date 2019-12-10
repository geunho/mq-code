package dev.geunho.mq_kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TransactionalProducer {
  private final KafkaProducer<String, Object> producer;
  private final String topic;

  public TransactionalProducer(String bootstrapServers, String topic) {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // exactly once semantics
    producer = new KafkaProducer<>(props);
    this.topic = topic;
  }

  public void send(Message message) {
    producer.send(new ProducerRecord<>(topic,
        message.getId(),
        message.getContents()));
  }
}
