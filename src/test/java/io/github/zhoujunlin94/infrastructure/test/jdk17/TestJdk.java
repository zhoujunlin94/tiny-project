package io.github.zhoujunlin94.infrastructure.test.jdk17;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhoujunlin
 * @date 2025-04-11-9:46
 */
public class TestJdk {

    @Test
    public void test1() {
        // 支持文本块
        String a = """
                dasdas
                dasdsdsd
                dasdsad 文本快
                """;
    }


    @Test
    public void test2() {
        // 空指针会显示具体null对象
        try {
            //简单的空指针
            String str = null;
            str.length();
        } catch (Exception e) {
            // Cannot invoke "String.length()" because "str" is null
            e.printStackTrace();
        }

        try {
            //复杂一点的空指针
            var arr = List.of(null);
            String str = (String) arr.get(0);
            str.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        // record可替代vo dto
        User user = new User("zhoujunlin", 30);
        System.out.println(user);
    }

    @Test
    public void test4() {
        System.out.println(getByJDK17(Week.SUNDAY));
    }

    public int getByJDK17(Week week) {
        // 1, 现在的switch变成了表达式，可以返回值了，而且支持yield和->符号来返回值
        // 2, 再也不用担心漏写了break，而导致出问题了
        // 3, case后面支持写多个条件
        return switch (week) {
            case null -> -1;
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> {
                yield 4;
            }
            case FRIDAY -> 5;
            case SATURDAY, SUNDAY -> 6;
            default -> 0;
        };
    }

    @Test
    public void test5() {
        matchByJDK17("111");
    }

    @Test
    public void test6() {
        Set<String> set = Set.of("a", "b", "c");
        System.out.println(set);
        // 不可变集合  不能再添加
//        set.add("d");
    }


    @Test
    public void test7() {
        System.out.println("a".repeat(5));
        System.out.println("".isBlank());
        System.out.println(" a   d   ".trim());
        String a = """
                dasdas
                dasdsdsd
                dasdsad 文本快
                """;
        System.out.println(a.lines().collect(Collectors.toList()));
        System.out.println(a.indent(4));
        String transform = "   data   ".transform(String::trim).transform(String::toUpperCase);
        System.out.println(transform);
    }


    public void matchByJDK17(Object value) {
        // 转换并申请了一个新的变量，极大地方便了代码的编写
        if (value instanceof String v) {
            System.out.println("遇到一个String类型" + v.toUpperCase());
        } else if (value instanceof Integer v) {
            System.out.println("遇到一个整型类型" + v.longValue());
        }
    }

    private enum Week {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

}
