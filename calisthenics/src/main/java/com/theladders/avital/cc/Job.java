package com.theladders.avital.cc;

import java.util.Objects;

public class Job {
    private final String name;
    private JobType type;
    private Employer employer;

    public Job(String name, JobType type) {
        this(name, type, null);
    }

    public Job(String name, JobType type, Employer employer) {
        this.name = name;
        this.type = type;
        this.employer = employer;
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

    public boolean isSameEmployer(String employerName) {
        return employerName.equals(employer.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Job job = (Job) other;
        return Objects.equals(name, job.name) &&
                type == job.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    public Employer getEmployer() {
        return employer;
    }

    boolean isSameName(String jobName) {
        return name.equals(jobName);
    }

    public String getEmployerName() {
        return employer.getName();
    }
}
