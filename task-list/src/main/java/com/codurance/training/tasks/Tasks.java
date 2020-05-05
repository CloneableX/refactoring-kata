package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tasks {
    private List<Task> tasks = new ArrayList<>();
    private PrintWriter out;

    public Tasks(PrintWriter out) {
        this.out = out;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void setDone(int taskId, boolean done) {
        Optional<Task> task = tasks.stream()
                .filter(taskItem -> taskItem.sameId(taskId))
                .findFirst();
        if (task.isPresent()) {
            task.get().setDone(done);
            return;
        }

        out.printf("Could not find a task with an ID of %d.", taskId);
        out.println();
    }

    public void checkTask(String taskId) {
        setDone(Integer.parseInt(taskId), true);
    }

    public void uncheckTask(String taskId) {
        setDone(Integer.parseInt(taskId), false);
    }

    public void showAll() {
        tasks.forEach(Task::formatPrint);
    }
}
