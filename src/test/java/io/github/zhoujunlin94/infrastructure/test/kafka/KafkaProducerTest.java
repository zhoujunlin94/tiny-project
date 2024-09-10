package io.github.zhoujunlin94.infrastructure.test.kafka;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.zhoujunlin94.common.SettingContext;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author zhoujunlin
 * @date 2024-09-10-11:34
 */
public class KafkaProducerTest {

    public static void main(String[] args) {
        Properties kafkaProps = SettingContext.getSetting("kafka", "kafka-default").toProperties();
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(kafkaProps);

        for (int i = 0; i < 10; i++) {
            JSONObject msg = new JSONObject().fluentPut("id", i).fluentPut("msgId", IdUtil.fastSimpleUUID());
            kafkaProducer.send(new ProducerRecord<>("flink-kafka-test", msg.toJSONString()));
        }

        kafkaProducer.close();
    }

}
