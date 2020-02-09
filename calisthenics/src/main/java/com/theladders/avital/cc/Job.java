package com.theladders.avital.cc;

public class Job {
    private final String name;
    private final String type;

    public Job(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
