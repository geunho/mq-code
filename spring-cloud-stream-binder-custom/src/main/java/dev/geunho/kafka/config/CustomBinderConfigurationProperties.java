package dev.geunho.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaBinderConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.stream.custom.binder")
public class CustomBinderConfigurationProperties {
    private final KafkaBinderConfigurationProperties kafka;

    private BrokerType type = BrokerType.kafka;
    private CustomBinderWatchProperties watch;

    public CustomBinderConfigurationProperties(KafkaBinderConfigurationProperties kafkaProperties) {
        this.kafka = kafkaProperties;
    }

    public KafkaBinderConfigurationProperties getKafka() {
        return this.kafka;
    }

    public BrokerType getType() {
        return this.type;
    }

    public enum BrokerType {
        kafka,
        http,
    }
}
