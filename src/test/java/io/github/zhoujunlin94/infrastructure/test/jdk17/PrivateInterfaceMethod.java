package io.github.zhoujunlin94.infrastructure.test.jdk17;

/**
 * @author zhoujunlin
 * @date 2025-04-11-10:03
 */
public interface PrivateInterfaceMethod {
    /**
     * 接口默认方法
     */
    default void defaultMethod() {
        privateMethod();
    }

    // 接口私有方法，在Java8里面是不被允许的，不信你试试
    private void privateMethod() {

    }

}
