package dev.geunho.kafka;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomSource extends Source {
    String OUTPUT = "custom-output";

    @Output(CustomSource.OUTPUT)
    MessageChannel output();
}
