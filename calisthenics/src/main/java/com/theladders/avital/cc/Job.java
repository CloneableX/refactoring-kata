package com.theladders.avital.cc;

public class Job {
    private final String name;
    private String type;
    private JobType jobType;

    public Job(String name, JobType jobType) {
        this.name = name;
        this.jobType = jobType;
        this.type = jobType.getName();
    }

    public Job(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public JobType getJobType() {
        return jobType;
    }
}
