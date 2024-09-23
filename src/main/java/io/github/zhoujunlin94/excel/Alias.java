package io.github.zhoujunlin94.excel;

import cn.hutool.core.util.StrUtil;

import java.lang.annotation.*;

/**
 * excel列别名
 *
 * @author zhoujl
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Alias {

    /**
     * Excel列名
     */
    String columnName() default StrUtil.EMPTY;

}
