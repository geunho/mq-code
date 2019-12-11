package dev.geunho.mq_kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class TransactionalProducer {
  private final KafkaProducer<String, String> producer;
  private final String topic;

  public TransactionalProducer(String bootstrapServers, String topic) {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true); // exactly once semantics
    producer = new KafkaProducer<>(props);
    this.topic = topic;
  }

  public void send(String contents) {
    Message message = new Message(contents);
//    producer.initTransactions();
//    producer.beginTransaction();
    producer.send(new ProducerRecord<>(topic,
        message.getId(),
        message.getContents()), (metadata, exception) -> {
          if (metadata != null) {
            System.out.println("topic: " + metadata.topic()
                + ", partition: " + metadata.partition()
                + ", offset: " + metadata.partition());

          } else if (exception != null) {
            exception.printStackTrace();
          }
    });
  }
}