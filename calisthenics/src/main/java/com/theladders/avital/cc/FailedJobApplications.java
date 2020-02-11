package com.theladders.avital.cc;

import java.util.ArrayList;
import java.util.List;

public class FailedJobApplications {
    private List<JobApplication> failedApplicationsTemp = new ArrayList<>();

    public void saveJobApplication(JobApplication jobApplication) {
        failedApplicationsTemp.add(jobApplication);
    }

    public int count(String employerName, String jobName) {
        return (int) failedApplicationsTemp.stream().filter(job ->
                job.isSameJobName(jobName) && job.isSameEmployerName(employerName)).count();
    }
}
