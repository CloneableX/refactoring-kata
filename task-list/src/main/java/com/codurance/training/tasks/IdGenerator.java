package com.codurance.training.tasks;

public class IdGenerator {
    private long lastId = 0;

    public long generateId() {
        return ++lastId;
    }
}
