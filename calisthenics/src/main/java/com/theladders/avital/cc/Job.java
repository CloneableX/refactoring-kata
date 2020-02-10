package com.theladders.avital.cc;

public class Job {
    private final String name;
    private JobType jobType;

    public Job(String name, JobType jobType) {
        this.name = name;
        this.jobType = jobType;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return jobType.getName();
    }

    public JobType getType() {
        return jobType;
    }
}
