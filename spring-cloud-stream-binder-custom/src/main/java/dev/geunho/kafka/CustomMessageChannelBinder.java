package dev.geunho.kafka;

import dev.geunho.kafka.config.CustomConsumerProperties;
import dev.geunho.kafka.config.CustomProducerProperties;
import org.springframework.cloud.stream.binder.*;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaConsumerProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaProducerProperties;
import org.springframework.cloud.stream.binder.kafka.provisioning.KafkaTopicProvisioner;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.config.MessageSourceCustomizer;
import org.springframework.cloud.stream.provisioning.ConsumerDestination;
import org.springframework.cloud.stream.provisioning.ProducerDestination;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

public class CustomMessageChannelBinder extends
        AbstractMessageChannelBinder<ExtendedConsumerProperties<KafkaConsumerProperties>, ExtendedProducerProperties<KafkaProducerProperties>, KafkaTopicProvisioner>
        implements
        ExtendedPropertiesBinder<MessageChannel, KafkaConsumerProperties, KafkaProducerProperties> {

    private final KafkaMessageChannelBinder kafkaBinder;

    public CustomMessageChannelBinder(String[] headersToEmbed, KafkaTopicProvisioner provisioningProvider, KafkaMessageChannelBinder kafkaBinder) {
        super(headersToEmbed, provisioningProvider);
        this.kafkaBinder = kafkaBinder;
    }

    public CustomMessageChannelBinder(String[] headersToEmbed, KafkaTopicProvisioner provisioningProvider, ListenerContainerCustomizer<?> containerCustomizer, MessageSourceCustomizer<?> sourceCustomizer, KafkaMessageChannelBinder kafkaBinder) {
        super(headersToEmbed, provisioningProvider, containerCustomizer, sourceCustomizer);
        this.kafkaBinder = kafkaBinder;
    }

    @Override
    public CustomConsumerProperties getExtendedConsumerProperties(String channelName) {
        return null;
    }

    @Override
    public CustomProducerProperties getExtendedProducerProperties(String channelName) {
        return null;
    }

    @Override
    public String getDefaultsPrefix() {
        return null;
    }

    @Override
    public Class<? extends BinderSpecificPropertiesProvider> getExtendedPropertiesEntryClass() {
        return null;
    }

    @Override
    protected MessageHandler createProducerMessageHandler(ProducerDestination destination, ExtendedProducerProperties<KafkaProducerProperties> producerProperties, MessageChannel errorChannel) throws Exception {
        return null;
    }

    @Override
    protected MessageProducer createConsumerEndpoint(ConsumerDestination destination, String group, ExtendedConsumerProperties<KafkaConsumerProperties> properties) throws Exception {
        return null;
    }
}
