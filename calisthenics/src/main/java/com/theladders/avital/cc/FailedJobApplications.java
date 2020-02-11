package com.theladders.avital.cc;

import java.util.ArrayList;
import java.util.List;

public class FailedJobApplications {
    private List<List<String>> failedApplications = new ArrayList<>();

    public void saveJobApplication(JobApplication jobApplication) {
        List<String> failedApplication = new ArrayList<>() {{
            add(jobApplication.getJobName());
            add(jobApplication.getJobTypeName());
            add(jobApplication.getApplicationTime("yyyy-MM-dd"));
            add(jobApplication.getEmployerName());
        }};
        failedApplications.add(failedApplication);
    }

    public int getUnsuccessfulApplications(String employerName, String jobName) {
        return (int) failedApplications.stream().filter(job -> job.get(0).equals(jobName) && job.get(3).equals(employerName)).count();
    }
}
