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
public @interface ExcelColumn {

    /**
     * Excel列名
     */
    String columnName() default StrUtil.EMPTY;

    /**
     * Excel列序号
     */
    int columnIndex() default -1;

    /**
     * Excel列宽度  最大255字符
     */
    int width() default -1;

}
