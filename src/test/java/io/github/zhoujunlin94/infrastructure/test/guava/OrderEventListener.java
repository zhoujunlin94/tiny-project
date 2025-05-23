package io.github.zhoujunlin94.infrastructure.test.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author zhoujunlin
 * @date 2025-05-23-13:40
 */
public class OrderEventListener {

    @Subscribe
    public void handle(OrderCreatedEvent event) {
        System.out.println("监听到订单创建事件：" + event.getOrderId());
    }

}
