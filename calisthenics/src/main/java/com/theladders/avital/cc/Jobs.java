package com.theladders.avital.cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jobs {
    private Map<String, List<List<String>>> jobsMap = new HashMap<>();

    public void saveJob(Employer employer, Job job) {
        List<List<String>> jobs = jobsMap.getOrDefault(employer.getName(), new ArrayList<>());

        jobs.add(new ArrayList<>() {{
            add(job.getName());
            add(job.getTypeName());
        }});
        jobsMap.put(employer.getName(), jobs);
    }

    public List<List<String>> getJobs(String employerName) {
        return jobsMap.get(employerName);
    }

    public void publishJob(Employer employer, Job job) throws EmptyJobTypeException {
        job.isEmptyType();
        saveJob(employer, job);
    }

}
