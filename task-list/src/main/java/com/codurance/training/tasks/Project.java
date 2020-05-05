package com.codurance.training.tasks;

import java.io.PrintWriter;

public class Project {
    private final Tasks tasks;
    private PrintWriter out;
    private String name;

    public Project(String name, PrintWriter out) {
        this.name = name;
        tasks = new Tasks(out);
        this.out = out;
    }

    public boolean sameName(String name) {
        return this.name.equals(name);
    }

    public void addTask(Task task) {
        tasks.addTask(task);
    }

    public void showAllTasks() {
        tasks.showAll();
    }

    public void formatPrint() {
        out.println(this.name);
        showAllTasks();
        out.println();
    }
}
