package io.github.zhoujunlin94.infrastructure.test.kafka;

import cn.hutool.core.collection.CollUtil;
import io.github.zhoujunlin94.common.SettingContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Properties;

/**
 * @author zhoujunlin
 * @date 2024-09-10-11:45
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        Properties kafkaProps = SettingContext.getSetting("kafka", "kafka-default").toProperties();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProps);
        consumer.subscribe(CollUtil.newArrayList("flink-kafka-test"));

        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println("消费到数据：" + consumerRecord.value());
            }
        }
    }

}
