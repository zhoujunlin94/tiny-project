package io.github.zhoujunlin94.infrastructure.test.excel;

import io.github.zhoujunlin94.excel.Alias;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhoujunlin
 * @date 2024-09-23-17:09
 * 试卷名字	题目id	题干	错误率
 */
@Data
public class Question {

    @Alias(columnName = "题目id")
    private Integer paperId;

    @Alias(columnName = "试卷名字")
    private String paperName;

    @Alias(columnName = "错误率")
    private BigDecimal errorRate;

    @Alias(columnName = "题干")
    private String questionContent;

}
