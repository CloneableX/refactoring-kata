package com.codurance.training.tasks;

public class Project {
    private String name;

    public Project(String name) {
        this.name = name;
    }

    public boolean checkSameName(String name) {
        return this.name.equals(name);
    }
}
