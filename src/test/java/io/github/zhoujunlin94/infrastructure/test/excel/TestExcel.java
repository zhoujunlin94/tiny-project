package io.github.zhoujunlin94.infrastructure.test.excel;

import cn.hutool.core.map.MapUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import io.github.zhoujunlin94.excel.AliasUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024-09-23-16:50
 */
public class TestExcel {

    public static void main(String[] args) {
        String bookFilePath = "D:\\Users\\Administrator\\Desktop\\中级会计二模试卷Top20错题.xlsx";
        Map<String, String> headAlias = AliasUtil.headAlias(Question.class);

        List<Question> fromQuestions = new LinkedList<>();
        for (int i = 0; i < ExcelUtil.getReader(bookFilePath).getSheetCount(); i++) {
            ExcelReader reader = ExcelUtil.getReader(bookFilePath, i);
            reader.setHeaderAlias(headAlias);
            reader.setIgnoreEmptyRow(true);
            fromQuestions.addAll(reader.readAll(Question.class));
        }

        Map<String, String> reverseHeadAlias = MapUtil.reverse(headAlias);
        ExcelWriter writer = ExcelUtil.getWriter("D:\\Users\\Administrator\\Desktop\\中级会计二模试卷Top20错题_copy.xlsx");
        writer.setHeaderAlias(reverseHeadAlias).autoSizeColumnAll();
        writer.write(fromQuestions).flush().close();

    }

}
