package com.cloneable;

public class Animal {
    private String name;
    private String desc;

    public Animal(String name, String desc) {
        this(name);
        this.desc = desc;
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String swallowed() {
        return Song.swallowAnimal(this) + ";\n" +
                desc + "\n";
    }
}
