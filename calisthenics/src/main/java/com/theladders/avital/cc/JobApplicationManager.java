package com.theladders.avital.cc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JobApplicationManager {
    private Map<String, List<List<String>>> jobApplicationMap = new HashMap<>();
    private Map<String, List<JobApplication>> jobApplicationMapTemp = new HashMap<>();

    public void applyJob(JobApplication jobApplication) {
        List<List<String>> saved = jobApplicationMap.getOrDefault(jobApplication.getJobSeeker().getName(), new ArrayList<>());

        saved.add(new ArrayList<>() {{
            add(jobApplication.getJob().getName());
            add(jobApplication.getJob().getTypeName());
            add(jobApplication.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            add(jobApplication.getJob().getEmployer().getName());
            add(jobApplication.getJobSeeker().getName());
        }});
        jobApplicationMap.put(jobApplication.getJobSeeker().getName(), saved);
        applyJobTemp(jobApplication);
    }

    public void applyJobTemp(JobApplication jobApplication) {
        List<JobApplication> jobApplications = jobApplicationMapTemp.getOrDefault(jobApplication.getJobSeeker().getName(), new ArrayList<>());
        jobApplications.add(jobApplication);
        jobApplicationMapTemp.put(jobApplication.getJobSeeker().getName(), jobApplications);
    }

    public List<JobApplication> getJobApplications(String jobSeekerName) {
        return jobApplicationMapTemp.get(jobSeekerName);
    }

    public List<String> findJobApplications(String jobName) {
        return findJobApplicationsBy(job -> job.get(0).equals(jobName));
    }

    public List<String> findJobApplications(DateRange dateRange) {
        return findJobApplicationsBy(job -> dateRange.isBetween(LocalDate.parse(job.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    public List<String> findJobApplications(String jobName, DateRange dateRange) {
        return findJobApplicationsBy(job -> job.get(0).equals(jobName) && dateRange.isBetween(LocalDate.parse(job.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    private List<String> findJobApplicationsBy(Predicate<List<String>> predicate) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            String applicant = set.getKey();
            List<List<String>> jobs = set.getValue();
            boolean hasAppliedToThisJob = jobs.stream().anyMatch(predicate);
            isMatchJobApplication(result, applicant, hasAppliedToThisJob);
        }
        return result;
    }

    public String exportHtml(LocalDate date) {
        List<List<String>> appliedOnDate = findJobApplications(job -> job.get(2).equals(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        String content = "";
        content = buildHtmlContent(content, appliedOnDate);

        return "<!DOCTYPE html>"
                + "<body>"
                + "<table>"
                + "<thead>"
                + "<tr>"
                + "<th>Employer</th>"
                + "<th>Job</th>"
                + "<th>Job Type</th>"
                + "<th>Applicants</th>"
                + "<th>Date</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>"
                + content
                + "</tbody>"
                + "</table>"
                + "</body>"
                + "</html>";
    }

    private String buildHtmlContent(String content, List<List<String>> appliedOnDate) {
        for (List<String> job : appliedOnDate) {
            content = content.concat("<tr>" + "<td>" + job.get(3) + "</td>" + "<td>" + job.get(0) + "</td>" + "<td>" + job.get(1) + "</td>" + "<td>" + job.get(4) + "</td>" + "<td>" + job.get(2) + "</td>" + "</tr>");
        }
        return content;
    }

    public String buildCvsContent(LocalDate date, String result) {
        List<List<String>> appliedOnDate = findJobApplications(job -> job.get(2).equals(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        result = buildCvsItem(result, appliedOnDate);
        return result;
    }

    private List<List<String>> findJobApplications(Predicate<List<String>> predicate) {
        List<List<String>> appliedOnDate = new ArrayList<>();
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            List<List<String>> jobApplications = set.getValue();
            appliedOnDate.addAll(jobApplications.stream().filter(predicate).collect(Collectors.toList()));
        }
        return appliedOnDate;
    }

    private String buildCvsItem(String result, List<List<String>> appliedOnDate) {
        for (List<String> job : appliedOnDate) {
            result = result.concat(job.get(3) + "," + job.get(0) + "," + job.get(1) + "," + job.get(4) + "," + job.get(2) + "\n");
        }
        return result;
    }

    public int countJobApplications(String employerName, String jobName) {
        List<List<String>> jobApplications = findJobApplications(job -> job.get(3).equals(employerName) && job.get(0).equals(jobName));
        return jobApplications.size();
    }

    private void isMatchJobApplication(List<String> result, String applicant, boolean isAppliedThisDate) {
        if (isAppliedThisDate) {
            result.add(applicant);
        }
    }
}
