package dev.geunho.kafka;

import org.springframework.cloud.stream.binder.BinderSpecificPropertiesProvider;
import org.springframework.cloud.stream.binder.ExtendedPropertiesBinder;
import org.springframework.cloud.stream.binder.kafka.KafkaBindingRebalanceListener;
import org.springframework.cloud.stream.binder.kafka.KafkaMessageChannelBinder;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaConsumerProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaProducerProperties;
import org.springframework.cloud.stream.binder.kafka.provisioning.KafkaTopicProvisioner;
import org.springframework.cloud.stream.binder.kafka.utils.DlqPartitionFunction;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.config.MessageSourceCustomizer;
import org.springframework.integration.kafka.inbound.KafkaMessageSource;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

import java.util.Map;

public class CustomMessageChannelBinder extends KafkaMessageChannelBinder
        implements ExtendedPropertiesBinder<MessageChannel, KafkaConsumerProperties, KafkaProducerProperties> {

    public CustomMessageChannelBinder(KafkaBinderConfigurationProperties configurationProperties, KafkaTopicProvisioner provisioningProvider) {
        super(configurationProperties, provisioningProvider);
    }

    public CustomMessageChannelBinder(KafkaBinderConfigurationProperties configurationProperties, KafkaTopicProvisioner provisioningProvider, ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> containerCustomizer, KafkaBindingRebalanceListener rebalanceListener) {
        super(configurationProperties, provisioningProvider, containerCustomizer, rebalanceListener);
    }

    public CustomMessageChannelBinder(KafkaBinderConfigurationProperties configurationProperties, KafkaTopicProvisioner provisioningProvider, ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> containerCustomizer, MessageSourceCustomizer<KafkaMessageSource<?, ?>> sourceCustomizer, KafkaBindingRebalanceListener rebalanceListener, DlqPartitionFunction dlqPartitionFunction) {
        super(configurationProperties, provisioningProvider, containerCustomizer, sourceCustomizer, rebalanceListener, dlqPartitionFunction);
    }

    /*
    @Override
    public KafkaConsumerProperties getExtendedConsumerProperties(String channelName) {
        return null;
    }

    @Override
    public KafkaProducerProperties getExtendedProducerProperties(String channelName) {
        return null;
    }

    @Override
    public Map<String, ?> getBindings() {
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
     */
}
