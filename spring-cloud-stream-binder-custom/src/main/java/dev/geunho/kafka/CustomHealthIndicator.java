package dev.geunho.kafka;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Kafka broker down 여부를 확인할 수 있는 HealthIndicator
 */
@Component
@EnableConfigurationProperties(CustomHealthIndicatorConfigurationProperties.class)
public class CustomHealthIndicator implements HealthIndicator {
  private final Logger log = LoggerFactory.getLogger(CustomHealthIndicator.class);

  @Autowired
  private final KafkaTemplate<> kafka;


  @Autowired
  public CustomHealthIndicator(CustomHealthIndicatorConfigurationProperties properties) {
//    Map<String, Object> configProps = new HashMap<>();
//    configProps.put(properties.BOOTSTRAP_SERVERS_CONFIG, properties.bootstrapAddress);
//    configProps.put(properties.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//    configProps.put(properties.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//    this.kafka = new KafkaTemplate<String, String>(new DefaultKafkaProducerFactory<>(configProps));
  }

  @Override
  public Health health() {
    try {
      kafka.metrics();
      kafka.send("kafka-health-indicator", "✓").get(100, TimeUnit.MILLISECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      return Health.down(e).build();
    }
    return Health.up().build();
  }
}