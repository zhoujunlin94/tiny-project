package io.github.zhoujunlin94.infrastructure.test.jdk17;

public record User(String name, int age) {

    public User {
        System.out.println("构造函数");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
    }

}
