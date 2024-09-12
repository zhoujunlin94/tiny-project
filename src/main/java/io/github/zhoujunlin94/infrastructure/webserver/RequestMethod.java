package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.*;

/**
 * @author zhoujl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMethod {

    String path() default StrUtil.EMPTY;

    String method() default "GET";

}
