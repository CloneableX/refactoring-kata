package com.codurance.training.tasks;

public class IdGenerator {
    private static long lastId = 0;

    public static long generateId() {
        return ++lastId;
    }
}
