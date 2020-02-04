package dev.geunho.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.binder.AbstractExtendedBindingProperties;
import org.springframework.cloud.stream.binder.BinderSpecificPropertiesProvider;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaBindingProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaConsumerProperties;
import org.springframework.cloud.stream.binder.kafka.properties.KafkaProducerProperties;

import java.util.Map;

@ConfigurationProperties("spring.cloud.stream.custom")
public class CustomExtendedBindingProperties extends
        AbstractExtendedBindingProperties<KafkaConsumerProperties, KafkaProducerProperties, KafkaBindingProperties> {
    private static final String DEFAULTS_PREFIX = "spring.cloud.stream.custom.default";


    @Override
    public Map<String, KafkaBindingProperties> getBindings() {
        return this.doGetBindings();
    }

    @Override
    public String getDefaultsPrefix() {
        return DEFAULTS_PREFIX;
    }

    @Override
    public Class<? extends BinderSpecificPropertiesProvider> getExtendedPropertiesEntryClass() {
        return KafkaBindingProperties.class;
    }
}
