package io.github.zhoujunlin94.infrastructure.test.kafka;

import cn.hutool.core.map.MapUtil;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

/**
 * @author zhoujunlin
 * @date 2024-09-25-14:13
 */
public class KafkaMonitoringDemo {

    public static void main(String[] args) {
        try {
            // Kafka JMX 连接 URL，端口号根据实际情况进行替换
            String jmxURL = "service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi";
            JMXServiceURL serviceURL = new JMXServiceURL(jmxURL);

            // 创建 JMX 连接
            JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, MapUtil.empty());
            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();

            // 获取特定的 MBean，这里以 Kafka 的 Controller 为例
            ObjectName objectName = new ObjectName("kafka.controller:type=ControllerStats");
            Set<ObjectName> names = mBeanServerConnection.queryNames(objectName, null);

            for (ObjectName name : names) {
                // 获取并打印 MBean 的属性值
                String activeControllerCount = mBeanServerConnection.getAttribute(name, "ActiveControllerCount").toString();
                System.out.println("ActiveControllerCount: " + activeControllerCount);
            }

            // 关闭 JMX 连接
            jmxConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
