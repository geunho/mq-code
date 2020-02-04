package dev.geunho.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
@EnableBinding(CustomSource.class)
public class CustomProducer<T> {

    private CustomSource source;

    @Autowired
    public CustomProducer(CustomSource source) {
        this.source = source;
    }

    public boolean send(T payload) {
        return this.source
                .output()
                .send(MessageBuilder.withPayload(payload)
                        .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                        .build());
    }
}
