package io.github.zhoujunlin94.infrastructure.test.kafka;

import io.github.zhoujunlin94.common.SettingContext;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * @author zhoujunlin
 * @date 2024-09-25-13:53
 */
public class KafkaProducerDemo01 {

    public static void main(String[] args) {
        // 设置生产者配置
        Properties producerProps = SettingContext.getSetting("kafka", "kafka-default.producer").toProperties();
        // 设置确认级别为 all
        producerProps.put(ProducerConfig.ACKS_CONFIG, "all");
        // 设置重试次数
        producerProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 设置重试间隔
        producerProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 200);

        // 创建 Kafka 生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

        // 创建消息
        String topicName = "flink-kafka-test";
        String message = "Hello, Kafka!";

        // 发送消息
        try {
            producer.send(new ProducerRecord<>(topicName, message), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    if (e != null) {
                        // 发送失败时  记录下来等待重试
                        e.printStackTrace();
                    } else {
                        System.out.println("Sent message with offset: " + metadata.offset());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭生产者
        producer.close();
    }

}
