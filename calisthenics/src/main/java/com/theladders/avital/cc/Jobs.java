package com.theladders.avital.cc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Jobs {
    private List<Job> jobs = new ArrayList<>();

    public void saveJob(Employer employer, Job job) {
        jobs.add(new Job(job.getName(), job.getType(), employer));
    }

    public List<List<String>> getJobs(String employerName) {
        return jobs.stream()
                .filter(job -> job.isSameEmployer(employerName))
                .map(job -> new ArrayList<String>() {{
                    add(job.getName());
                    add(job.getTypeName());
                }})
                .collect(Collectors.toList());
    }

    public void publishJob(Employer employer, Job job) throws EmptyJobTypeException {
        job.isEmptyType();
        saveJob(employer, job);
    }

}
