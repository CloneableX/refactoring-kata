package com.theladders.avital.cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Jobs {
    private Map<String, List<Job>> jobsMap = new HashMap<>();

    public void saveJob(Employer employer, Job job) {
        List<Job> jobs = jobsMap.getOrDefault(employer.getName(), new ArrayList<>());

        jobs.add(job);
        jobsMap.put(employer.getName(), jobs);
    }

    public List<List<String>> getJobs(String employerName) {
        List<Job> jobs = jobsMap.get(employerName);
        return jobs.stream()
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
