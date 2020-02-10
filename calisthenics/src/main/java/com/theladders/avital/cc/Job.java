package com.theladders.avital.cc;

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
}
