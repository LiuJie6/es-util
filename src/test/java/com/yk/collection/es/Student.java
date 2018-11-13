package com.yk.collection.es;

/**
 * Project Name:es-util
 * File Name:Student
 * Package Name:com.yk.collection.es
 * Date:2018/6/1 15:27
 * Author:wangzhili
 * Description:
 * Copyright (c) 2018, 重庆云凯科技有限公司 All Rights Reserved.
 */



public class Student {
    private String name;
    private String sex;

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
