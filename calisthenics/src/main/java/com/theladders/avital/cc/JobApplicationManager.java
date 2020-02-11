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
    }

    public List<List<String>> getJobApplications(String employerName) {
        return jobApplicationMap.get(employerName)
                .stream()
                .map(jobApplication -> new ArrayList<String>() {{
                    add(jobApplication.get(0));
                    add(jobApplication.get(1));
                    add(jobApplication.get(2));
                    add(jobApplication.get(3));
                }})
                .collect(Collectors.toList());
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
        String content = "";
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            String applicant = set.getKey();
            List<List<String>> jobs1 = set.getValue();
            List<List<String>> appliedOnDate = jobs1.stream().filter(job -> job.get(2).equals(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).collect(Collectors.toList());

            content = buildHtmlContent(content, appliedOnDate);
        }

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
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            String applicant = set.getKey();
            List<List<String>> jobs1 = set.getValue();
            List<List<String>> appliedOnDate = jobs1.stream().filter(job -> job.get(2).equals(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).collect(Collectors.toList());

            result = buildCvsItem(result, appliedOnDate);
        }
        return result;
    }

    private String buildCvsItem(String result, List<List<String>> appliedOnDate) {
        for (List<String> job : appliedOnDate) {
            result = result.concat(job.get(3) + "," + job.get(0) + "," + job.get(1) + "," + job.get(4) + "," + job.get(2) + "\n");
        }
        return result;
    }

    public int getSuccessfulApplications(String employerName, String jobName) {
        int result = 0;
        for (Map.Entry<String, List<List<String>>> set : jobApplicationMap.entrySet()) {
            List<List<String>> jobs = set.getValue();

            result += jobs.stream().anyMatch(job -> job.get(3).equals(employerName) && job.get(0).equals(jobName)) ? 1 : 0;
        }
        return result;
    }

    private void isMatchJobApplication(List<String> result, String applicant, boolean isAppliedThisDate) {
        if (isAppliedThisDate) {
            result.add(applicant);
        }
    }
}
