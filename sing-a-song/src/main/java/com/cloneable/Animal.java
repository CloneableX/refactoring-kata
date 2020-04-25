package com.cloneable;

public class Animal {
    private final String name;
    private final String desc;

    public Animal(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
