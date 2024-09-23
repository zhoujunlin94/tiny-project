package io.github.zhoujunlin94.excel;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanDesc;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024-09-23-17:14
 */
public final class AliasUtil {

    public static Map<String, String> headAlias(Class clazz) {
        BeanDesc beanDesc = new BeanDesc(clazz);
        Map<String, String> aliasNameMap = new LinkedHashMap<>();
        beanDesc.getProps().forEach(propDesc -> {
            Field field = propDesc.getField();
            String fieldName = field.getName();
            String columnName = fieldName;
            Alias annotation = AnnotationUtil.getAnnotation(field, Alias.class);
            if (Objects.nonNull(annotation)) {
                columnName = annotation.columnName();
            }
            aliasNameMap.put(columnName, fieldName);
        });
        return aliasNameMap;
    }

}
