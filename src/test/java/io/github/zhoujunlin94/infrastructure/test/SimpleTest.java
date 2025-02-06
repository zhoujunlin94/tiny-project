package io.github.zhoujunlin94.infrastructure.test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoujunlin
 * @date 2024-11-13-17:33
 */
@Slf4j
public class SimpleTest {

    public static void main(String[] args) throws Exception {

        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        String express = "a + b * c";
        Object r = runner.execute(express, context, null, true, false);
        System.out.println(r);

    }

}
