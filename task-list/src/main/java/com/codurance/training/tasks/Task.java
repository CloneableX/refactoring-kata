package com.codurance.training.tasks;

import java.io.PrintWriter;

public final class Task {
    private final long id = IdGenerator.generateId();
    private final String description;
    private PrintWriter out;
    private boolean done = false;

    public Task(String description, PrintWriter out) {
        this.description = description;
        this.out = out;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean sameId(int taskId) {
        return this.id == taskId;
    }

    public void formatPrint() {
        out.printf("    [%c] %d: %s%n", (done ? 'x' : ' '), id, description);
    }
}
