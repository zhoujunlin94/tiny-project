package io.github.zhoujunlin94.infrastructure.test.kafka;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.zhoujunlin94.core.SettingContext;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author zhoujunlin
 * @date 2024-09-10-11:34
 */
public class KafkaProducerTest {

    public static void main(String[] args) {
        Properties kafkaProps = SettingContext.getSetting("kafka", "kafka-default.producer").toProperties();
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaProps);

        for (int i = 0; i < 10; i++) {
            JSONObject msg = new JSONObject().fluentPut("id", i).fluentPut("msgId", IdUtil.fastSimpleUUID());
            kafkaProducer.send(new ProducerRecord<>("flink-kafka-test", i + "", msg.toJSONString()),
                    new Callback() {
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
        }

        kafkaProducer.close();
    }

}
