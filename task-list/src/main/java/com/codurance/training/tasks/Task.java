package com.codurance.training.tasks;

public final class Task {
    private final long id = IdGenerator.generateId();
    private final String description;
    private boolean done = false;

    public Task(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
