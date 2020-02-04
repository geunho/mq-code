package dev.geunho.kafka.config;

import dev.geunho.kafka.CustomMessageChannelBinder;
import dev.geunho.kafka.CustomProvisioningProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.kafka.KafkaBindingRebalanceListener;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.kafka.provisioning.KafkaTopicProvisioner;
import org.springframework.cloud.stream.binder.kafka.utils.DlqPartitionFunction;
import org.springframework.cloud.stream.config.ConsumerEndpointCustomizer;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.cloud.stream.config.MessageSourceCustomizer;
import org.springframework.cloud.stream.config.ProducerMessageHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.integration.kafka.inbound.KafkaMessageSource;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.lang.Nullable;

@Configuration
@ConditionalOnMissingBean(Binder.class)
@EnableConfigurationProperties({ CustomExtendedBindingProperties.class })
public class CustomBinderConfiguration {

    @Autowired
    private CustomExtendedBindingProperties customExtendedBindingProperties;

    @Autowired
    private KafkaBinderConfigurationProperties kafkaProperties;

    @Autowired
    private KafkaTopicProvisioner kafkaTopicProvisioner;

    @Bean
    CustomBinderConfigurationProperties configurationProperties(
            KafkaBinderConfigurationProperties kafkaProperties) {
        return new CustomBinderConfigurationProperties(kafkaProperties);
    }
//
//    @Bean
//    CustomProvisioningProvider provisioningProvider(
//            KafkaBinderConfigurationProperties configurationProperties) {
//        return new CustomProvisioningProvider(configurationProperties, this.kafkaTopicProvisioner);
//    }
//
//    @Bean
//    CustomMessageChannelBinder customMessageChannelBinder(
//            CustomBinderConfigurationProperties configurationProperties,
//            KafkaTopicProvisioner provisioningProvider,
//            @Nullable ListenerContainerCustomizer<AbstractMessageListenerContainer<?, ?>> listenerContainerCustomizer,
//            @Nullable MessageSourceCustomizer<KafkaMessageSource<?, ?>> sourceCustomizer,
//            @Nullable ProducerMessageHandlerCustomizer<KafkaProducerMessageHandler<?, ?>> messageHandlerCustomizer,
//            @Nullable ConsumerEndpointCustomizer<KafkaMessageDrivenChannelAdapter<?, ?>> consumerCustomizer,
//            ObjectProvider<KafkaBindingRebalanceListener> rebalanceListener,
//            ObjectProvider<DlqPartitionFunction> dlqPartitionFunction) {
//
//        CustomMessageChannelBinder customMessageChannelBinder = new CustomMessageChannelBinder(
//                configurationProperties, provisioningProvider,
//                listenerContainerCustomizer, sourceCustomizer, rebalanceListener.getIfUnique(),
//                dlqPartitionFunction.getIfUnique());
//
//        customMessageChannelBinder.setProducerListener(this.producerListener);
//        customMessageChannelBinder
//                .setExtendedBindingProperties(this.customExtendedBindingProperties);
//        customMessageChannelBinder.setProducerMessageHandlerCustomizer(messageHandlerCustomizer);
//        customMessageChannelBinder.setConsumerEndpointCustomizer(consumerCustomizer);
//        return customMessageChannelBinder;
//    }
}
