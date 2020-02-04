package dev.geunho.kafka;

import dev.geunho.kafka.config.CustomBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.ExtendedProducerProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaConsumerProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaProducerProperties;
import org.springframework.cloud.stream.binder.kafka.provisioning.KafkaTopicProvisioner;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.cloud.stream.provisioning.ProvisioningException;
import org.springframework.cloud.stream.provisioning.ProvisioningProvider;

public class CustomProvisioningProvider implements
        ProvisioningProvider<ExtendedConsumerProperties<KafkaConsumerProperties>, ExtendedProducerProperties<KafkaProducerProperties>> {

    private final CustomBinderConfigurationProperties configurationProperties;

    private final KafkaTopicProvisioner kafkaTopicProvisioner;

    public CustomProvisioningProvider(CustomBinderConfigurationProperties configurationProperties,
                                      KafkaTopicProvisioner kafkaTopicProvisioner) {
        this.configurationProperties = configurationProperties;
        this.kafkaTopicProvisioner = kafkaTopicProvisioner;
    }

    @Override
    public ProducerDestination provisionProducerDestination(
            String name,
            ExtendedProducerProperties<KafkaProducerProperties> properties) throws ProvisioningException {
        if (configurationProperties.getType() == CustomBinderConfigurationProperties.BrokerType.kafka) {
            return kafkaTopicProvisioner.provisionProducerDestination(name, properties);
        }
        else {
            throw new UnsupportedOperationException("Currently only Kafka broker is supported.");
        }
    }

    @Override
    public ConsumerDestination provisionConsumerDestination(
            String name,
            String group,
            ExtendedConsumerProperties<KafkaConsumerProperties> properties) throws ProvisioningException {
        if (configurationProperties.getType() == CustomBinderConfigurationProperties.BrokerType.kafka) {
            return kafkaTopicProvisioner.provisionConsumerDestination(name, group, properties);
        }
        else {
            throw new UnsupportedOperationException("Currently only Kafka broker is supported.");
        }
    }
}
