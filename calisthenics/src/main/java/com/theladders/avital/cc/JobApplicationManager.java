package com.theladders.avital.cc;

import java.time.LocalDate;
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

    public List<String> findJobApplicationsByJobNameAndStartDate(String jobName, LocalDate from) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            String applicant = set.getKey();
            List<List<String>> jobs = set.getValue();
            boolean isAppliedThisDate = jobs.stream().anyMatch(job -> job.get(0).equals(jobName) && !from.isAfter(LocalDate.parse(job.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            isMatchJobApplication(result, applicant, isAppliedThisDate);
        }
        return result;
    }

    private void isMatchJobApplication(List<String> result, String applicant, boolean isAppliedThisDate) {
        if (isAppliedThisDate) {
            result.add(applicant);
        }
    }

    public List<String> findJobApplicationsByJobNameAndEndDate(String jobName, LocalDate to) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            String applicant = set.getKey();
            List<List<String>> jobs = set.getValue();
            boolean isAppliedThisDate = jobs.stream().anyMatch(job -> job.get(0).equals(jobName) && !to.isBefore(LocalDate.parse(job.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            isMatchJobApplication(result, applicant, isAppliedThisDate);
        }
        return result;
    }
}
