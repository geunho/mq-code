package dev.geunho.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.health")
public class CustomHealthIndicatorConfigurationProperties {

  public static final String KEY_SERIALIZER_CLASS_CONFIG = "spring.kafka.producer.key-serializer";
  public static final String VALUE_SERIALIZER_CLASS_CONFIG = "spring.kafka.producer.value-serializer";
  public static final String BOOTSTRAP_SERVERS_CONFIG = "spring.kafka.bootstrap-servers";
}
