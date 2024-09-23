package io.github.zhoujunlin94.infrastructure.test.excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import io.github.zhoujunlin94.excel.AliasUtil;

import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-09-23-16:50
 */
public class TestExcel {

    public static void main(String[] args) {
        String bookFilePath = "D:\\Users\\Administrator\\Desktop\\中级会计二模试卷Top20错题.xlsx";
        Map<String, String> headAlias = AliasUtil.headAlias(Question.class);
        for (int i = 0; i < ExcelUtil.getReader(bookFilePath).getSheetCount(); i++) {
            ExcelReader reader = ExcelUtil.getReader(bookFilePath, i);
            reader.setHeaderAlias(headAlias);
            reader.setIgnoreEmptyRow(true);
            System.out.println(reader.readAll(Question.class));
        }
    }

}
