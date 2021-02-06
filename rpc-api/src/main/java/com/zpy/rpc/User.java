package com.zpy.rpc;

import java.io.Serializable;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 19:31
 */
public class User implements Serializable {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
