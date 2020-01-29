package dev.geunho.kafka;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CustomInterceptor
        implements ProducerInterceptor<String, String>, ConsumerInterceptor<String, String> {

    public static Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Override
    public void configure(Map<String, ?> configs) {
    }

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        logger.info("onConsume: " + records.count());
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        logger.info("onCommit: " + offsets.toString());
    }

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        logger.info("onSend: " + record);
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            logger.error("onAck: Failed to ack messages. " + exception);
        }

        logger.info("onAck: " + metadata);
    }

    @Override
    public void close() {
    }

}
