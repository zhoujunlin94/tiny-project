package io.github.zhoujunlin94.excel;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024-09-23-17:14
 */
public final class ExcelColumnUtil {

    public static Map<String, String> headAlias(Class clazz) {
        BeanDesc beanDesc = BeanUtil.getBeanDesc(clazz);
        Map<String, String> aliasNameMap = new LinkedHashMap<>();
        beanDesc.getProps().forEach(propDesc -> {
            Field field = propDesc.getField();
            String fieldName = field.getName();
            String columnName = fieldName;
            ExcelColumn annotation = AnnotationUtil.getAnnotation(field, ExcelColumn.class);
            if (Objects.nonNull(annotation)) {
                columnName = annotation.columnName();
            }
            aliasNameMap.put(columnName, fieldName);
        });
        return aliasNameMap;
    }

    public static void setColumnWidth(Class clazz, ExcelWriter writer) {
        BeanDesc beanDesc = BeanUtil.getBeanDesc(clazz);
        beanDesc.getProps().forEach(propDesc -> {
            Field field = propDesc.getField();
            ExcelColumn annotation = AnnotationUtil.getAnnotation(field, ExcelColumn.class);
            if (Objects.nonNull(annotation) && annotation.columnIndex() >= 0) {
                writer.setColumnWidth(annotation.columnIndex(), annotation.width());
            }
        });
    }

}
