package com.theladders.avital.cc;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FailedJobApplications {
    private List<List<String>> failedApplications = new ArrayList<>();

    public void saveJobApplication(Employer employer, Job job, JobApplication jobApplication) {
        List<String> failedApplication = new ArrayList<>() {{
            add(job.getName());
            add(job.getTypeName());
            add(jobApplication.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            add(employer.getName());
        }};
        failedApplications.add(failedApplication);
    }

    public int getUnsuccessfulApplications(String employerName, String jobName) {
        return (int) failedApplications.stream().filter(job -> job.get(0).equals(jobName) && job.get(3).equals(employerName)).count();
    }
}
