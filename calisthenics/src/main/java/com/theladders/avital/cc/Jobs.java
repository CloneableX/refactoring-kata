package com.theladders.avital.cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jobs {
    private Map<String, List<List<String>>> jobsMap = new HashMap<>();

    public void saveJob(Employer employer, Job job) {
        List<List<String>> saved = jobsMap.getOrDefault(employer.getName(), new ArrayList<>());

        saved.add(new ArrayList<>() {{
            add(job.getName());
            add(job.getTypeName());
        }});
        jobsMap.put(employer.getName(), saved);
    }

    public List<List<String>> getJobs(String employerName) {
        return jobsMap.get(employerName);
    }

    void publishJob(Employer employer, Job job) throws NotSupportedJobTypeException {
        if (job.getType() != JobType.JREQ && job.getType() != JobType.ATS) {
            throw new NotSupportedJobTypeException();
        }

        List<List<String>> alreadyPublished = jobsMap.getOrDefault(employer.getName(), new ArrayList<>());

        alreadyPublished.add(new ArrayList<>() {{
            add(job.getName());
            add(job.getTypeName());
        }});
        jobsMap.put(employer.getName(), alreadyPublished);
    }
}
