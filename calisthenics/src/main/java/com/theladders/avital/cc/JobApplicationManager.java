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
    private Map<String, List<JobApplication>> jobApplicationMap = new HashMap<>();

    public void applyJob(JobApplication jobApplication) {
        List<JobApplication> jobApplications = jobApplicationMap.getOrDefault(jobApplication.getJobSeekerName(), new ArrayList<>());
        jobApplications.add(jobApplication);
        jobApplicationMap.put(jobApplication.getJobSeekerName(), jobApplications);
    }

    public List<JobApplication> getJobApplications(String jobSeekerName) {
        return jobApplicationMap.get(jobSeekerName);
    }

    public List<String> findJobApplications(String jobName) {
        return findJobApplicationsBy(jobApplication -> jobApplication.isSameJobName(jobName));
    }

    public List<String> findJobApplications(DateRange dateRange) {
        return findJobApplicationsBy(jobApplication -> dateRange.isBetween(jobApplication.getApplicationTime()));
    }

    public List<String> findJobApplications(String jobName, DateRange dateRange) {
        return findJobApplicationsBy(jobApplication ->
                jobApplication.isSameJobName(jobName)
                        && dateRange.isBetween(jobApplication.getApplicationTime()));
    }

    private List<String> findJobApplicationsBy(Predicate<JobApplication> predicate) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<JobApplication>> set : jobApplicationMap.entrySet()) {
            String applicant = set.getKey();
            List<JobApplication> jobs = set.getValue();
            boolean hasAppliedToThisJob = jobs.stream().anyMatch(predicate);
            isMatchJobApplication(result, applicant, hasAppliedToThisJob);
        }
        return result;
    }

    public String exportHtml(LocalDate date) {
        List<JobApplication> appliedOnDate = findJobApplications(job ->
                job.getApplicationTime().equals(date));
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

    private String buildHtmlContent(String content, List<JobApplication> appliedOnDate) {
        for (JobApplication job : appliedOnDate) {
            content = content.concat("<tr>"
                    + "<td>"
                    + job.getJob().getEmployer().getName()
                    + "</td>"
                    + "<td>"
                    + job.getJob().getName()
                    + "</td>"
                    + "<td>"
                    + job.getJob().getTypeName()
                    + "</td>"
                    + "<td>"
                    + job.getJobSeekerName()
                    + "</td>"
                    + "<td>"
                    + job.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + "</td>"
                    + "</tr>");
        }
        return content;
    }

    public String buildCvsContent(LocalDate date, String result) {
        List<JobApplication> appliedOnDate = findJobApplications(job ->
                job.getApplicationTime().equals(date));
        result = buildCvsItem(result, appliedOnDate);
        return result;
    }

    private List<JobApplication> findJobApplications(Predicate<JobApplication> predicate) {
        List<JobApplication> appliedOnDate = new ArrayList<>();
        for (Map.Entry<String, List<JobApplication>> set : jobApplicationMap.entrySet()) {
            List<JobApplication> jobApplications = set.getValue();
            appliedOnDate.addAll(jobApplications.stream().filter(predicate).collect(Collectors.toList()));
        }
        return appliedOnDate;
    }

    private String buildCvsItem(String result, List<JobApplication> appliedOnDate) {
        for (JobApplication job : appliedOnDate) {
            result = result.concat(job.getJob().getEmployer().getName()
                    + ","
                    + job.getJob().getName()
                    + ","
                    + job.getJob().getTypeName()
                    + ","
                    + job.getJobSeekerName()
                    + ","
                    + job.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n");
        }
        return result;
    }

    public int countJobApplications(String employerName, String jobName) {
        List<JobApplication> jobApplications = findJobApplications(job ->
                job.getJob().getEmployer().getName().equals(employerName) && job.isSameJobName(jobName));
        return jobApplications.size();
    }

    private void isMatchJobApplication(List<String> result, String applicant, boolean isAppliedThisDate) {
        if (isAppliedThisDate) {
            result.add(applicant);
        }
    }
}
