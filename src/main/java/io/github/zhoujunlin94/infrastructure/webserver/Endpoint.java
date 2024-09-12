package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.*;

/**
 * 控制器注解
 *
 * @author zhoujl
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Endpoint {

    String path() default StrUtil.EMPTY;

}
