package com.codurance.training.tasks;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private List<Task> tasks = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public boolean sameName(String name) {
        return this.name.equals(name);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String getName() {
        return this.name;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
