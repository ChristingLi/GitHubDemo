package com.cn.bean;

/**
 * Created by ld on 2017/10/13.
 */

public class JsonEntity {
    private String name;
    private int age;

    public JsonEntity() {
        super();
    }

    public JsonEntity(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

