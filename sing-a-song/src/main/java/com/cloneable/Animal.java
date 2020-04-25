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

    public String swallowed() {
        String swallowDesc = "There was an old lady who swallowed a " + name;
        if (desc == null) {
            return swallowDesc;
        }

        return swallowDesc + ";\n" +
                desc + "\n";
    }

    public String getName() {
        return name;
    }
}
