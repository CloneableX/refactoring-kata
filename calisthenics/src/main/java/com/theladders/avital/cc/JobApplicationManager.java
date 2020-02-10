package com.theladders.avital.cc;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobApplicationManager {
    private Map<String, List<List<String>>> jobApplicationMap = new HashMap<>();

    public void applyJob(Employer employer, Job job, JobSeeker jobSeeker, JobApplication jobApplication) {
        List<List<String>> saved = jobApplicationMap.getOrDefault(jobSeeker.getName(), new ArrayList<>());

        saved.add(new ArrayList<>() {{
            add(job.getName());
            add(job.getTypeName());
            add(jobApplication.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            add(employer.getName());
        }});
        jobApplicationMap.put(jobSeeker.getName(), saved);
    }

    public List<List<String>> getJobApplications(String employerName) {
        return jobApplicationMap.get(employerName);
    }
}
