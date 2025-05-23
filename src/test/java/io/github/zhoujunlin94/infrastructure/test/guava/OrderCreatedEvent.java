package io.github.zhoujunlin94.infrastructure.test.guava;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhoujunlin
 * @date 2025-05-23-13:39
 */
@Getter
@RequiredArgsConstructor
public class OrderCreatedEvent {
    private final String orderId;
}
