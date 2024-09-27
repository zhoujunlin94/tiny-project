package io.github.zhoujunlin94.infrastructure.test.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author zhoujunlin
 * @date 2024-09-25-14:18
 */
public class KafkaConfigurationDemo {

    public static void main(String[] args) {
        // 生产者配置
        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 启用 GZIP 压缩
        producerProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        // 批量大小
        producerProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // 延迟时间
        producerProps.put(ProducerConfig.LINGER_MS_CONFIG, 20);
        // 缓冲区内存
        producerProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        // 最大请求大小，例如 10MB
        producerProps.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 10485760);

        // 创建生产者实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

        // 消费者配置
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "example-consumer-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 偏移量重置策略
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // 关闭自动提交偏移量
        consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // 创建消费者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        // 订阅主题
        consumer.subscribe(Collections.singletonList("exampleTopic"));

        // 发送消息示例
        producer.send(new ProducerRecord<>("exampleTopic", "key", "value"));

        // 接收消息示例
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            // 手动提交 offset
            consumer.commitSync(Collections.singletonMap(new TopicPartition(record.topic(), record.partition()),
                    new OffsetAndMetadata(record.offset() + 1)));
        }

        // 关闭生产者和消费者
        producer.close();
        consumer.close();
    }


}
