package io.github.zhoujunlin94.infrastructure.test.kafka;

import cn.hutool.core.collection.CollUtil;
import io.github.zhoujunlin94.core.SettingContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author zhoujunlin
 * @date 2024-09-10-11:45
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        Properties kafkaProps = SettingContext.getSetting("kafka", "kafka-default.consumer").toProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProps);
        consumer.subscribe(CollUtil.newArrayList("flink-kafka-test"));

        try {
            while (true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
                if (consumerRecords.isEmpty()) {
                    continue;
                }
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    try {
                        System.out.printf("offset = %d, key = %s, value = %s%n", consumerRecord.offset(), consumerRecord.key(), consumerRecord.value());

                        // 手动提交 offset
                        consumer.commitSync(Collections.singletonMap(
                                new TopicPartition(consumerRecord.topic(), consumerRecord.partition()),
                                new OffsetAndMetadata(consumerRecord.offset() + 1)
                        ));
                    } catch (Exception e) {
                        System.err.printf("Error processing message with offset %d: %s%n", consumerRecord.offset(), e.getMessage());
                    }
                }
            }
        } finally {
            consumer.close();
        }

    }

}
