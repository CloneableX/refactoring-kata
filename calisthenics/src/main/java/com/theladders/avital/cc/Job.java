package com.theladders.avital.cc;

public class Job {
    private final String name;
    private JobType type;

    public Job(String name, JobType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return type.getName();
    }

    public JobType getType() {
        return type;
    }

    public void isEmptyType() throws EmptyJobTypeException {
        if (type == null) {
            throw new EmptyJobTypeException();
        }
    }
}
