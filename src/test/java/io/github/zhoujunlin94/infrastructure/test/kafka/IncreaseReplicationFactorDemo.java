package io.github.zhoujunlin94.infrastructure.test.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author zhoujunlin
 * @date 2024-09-25-14:10
 */
public class IncreaseReplicationFactorDemo {

    public static void main(String[] args) {
        // 设置 AdminClient 配置
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 创建 AdminClient 实例
        try (AdminClient admin = AdminClient.create(props)) {
            // 定义要修改的主题和新的副本因子
            String topicName = "exampleTopic";
            int newReplicationFactor = 3;

            // 创建配置资源
            ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topicName);

            // 构建配置项
            Config newConfig = new Config(Collections.singletonList(new ConfigEntry("replication.factor", String.valueOf(newReplicationFactor))));

            // 修改主题配置
            AlterConfigsResult alterResult = admin.alterConfigs(Collections.singletonMap(resource, newConfig));

            // 等待操作完成
            alterResult.values().get(resource).get();

            System.out.println("Replication factor increased to " + newReplicationFactor);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
