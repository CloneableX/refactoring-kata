package com.theladders.avital.cc;

import java.time.LocalDate;
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
                job.isSameApplicationTime(date));

        return ExportTemplate.HTML_TEMPLATE.replace(ExportTemplate.CONTENT_TAG, buildHtmlContent(appliedOnDate));
    }

    private String buildHtmlContent(List<JobApplication> jobApplications) {
        return jobApplications.stream()
                .map(JobApplication::toHtmlString)
                .reduce("", String::concat);
    }

    public String buildCvsContent(LocalDate date) {
        List<JobApplication> jobApplications = findJobApplications(jobApplication ->
                jobApplication.isSameApplicationTime(date));
        return ExportTemplate.CVS.export(buildCvsItem(jobApplications));
    }

    private List<JobApplication> findJobApplications(Predicate<JobApplication> predicate) {
        List<JobApplication> appliedOnDate = new ArrayList<>();
        for (Map.Entry<String, List<JobApplication>> set : jobApplicationMap.entrySet()) {
            List<JobApplication> jobApplications = set.getValue();
            appliedOnDate.addAll(jobApplications.stream().filter(predicate).collect(Collectors.toList()));
        }
        return appliedOnDate;
    }

    private String buildCvsItem(List<JobApplication> jobApplications) {
        return jobApplications.stream()
                .map(JobApplication::toCvsString)
                .reduce("", String::concat);
    }

    public int countJobApplications(String employerName, String jobName) {
        List<JobApplication> jobApplications = findJobApplications(job ->
                job.isSameEmployerName(employerName) && job.isSameJobName(jobName));
        return jobApplications.size();
    }

    private void isMatchJobApplication(List<String> result, String applicant, boolean isAppliedThisDate) {
        if (isAppliedThisDate) {
            result.add(applicant);
        }
    }
}
