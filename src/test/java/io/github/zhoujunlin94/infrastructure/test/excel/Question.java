package io.github.zhoujunlin94.infrastructure.test.excel;

import io.github.zhoujunlin94.excel.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhoujunlin
 * @date 2024-09-23-17:09
 * 试卷名字	题目id	题干	错误率
 */
@Data
public class Question {

    @ExcelColumn(columnName = "题目id")
    private Integer paperId;

    @ExcelColumn(columnName = "试卷名字", columnIndex = 1, width = 30)
    private String paperName;

    @ExcelColumn(columnName = "错误率")
    private BigDecimal errorRate;

    @ExcelColumn(columnName = "题干", columnIndex = 3, width = 255)
    private String questionContent;

}
