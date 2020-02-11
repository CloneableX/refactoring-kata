package com.theladders.avital.cc;

import java.time.LocalDate;
import java.util.List;

public class Application {
    private Jobs jobs = new Jobs();
    private JobApplicationManager jobApplicationManager = new JobApplicationManager();
    private FailedJobApplications failedJobApplications = new FailedJobApplications();

    public void saveJob(Job job) {
        jobs.save(job);
    }

    public void publishJob(Job job) throws EmptyJobTypeException {
        jobs.publish(job);
    }

    public void applyJob(JobApplication jobApplication) throws RequiresResumeForJReqJobException, InvalidResumeException {
        try {
            jobApplication.isValidJobApplication();
        } catch (RequiresResumeForJReqJobException exception) {
            failedJobApplications.saveJobApplication(jobApplication);
            throw exception;
        }

        jobApplicationManager.applyJob(jobApplication);
    }

    public List<Job> getJobs(String employerName) {
        return jobs.getJobs(employerName);
    }

    public List<JobApplication> getJobApplications(String jobSeekerName) {
        return jobApplicationManager.getJobApplications(jobSeekerName);
    }

    public List<String> findApplicants(String jobName, DateRange dateRange) {
        if (dateRange.isEmpty()) {
            return jobApplicationManager.findJobApplications(jobName);
        }

        if (jobName == null) {
            return jobApplicationManager.findJobApplications(dateRange);
        }

        return jobApplicationManager.findJobApplications(jobName, dateRange);
    }

    public String exportCvs(LocalDate date) {
        return jobApplicationManager.buildCvsContent(date);
    }

    public String exportHtml(LocalDate date) {
        return jobApplicationManager.exportHtml(date);
    }

    public int countFailedJobApplications(String employerName, String jobName) {
        return failedJobApplications.count(employerName, jobName);
    }

    public int countSuccessJobApplications(String employerName, String jobName) {
        return jobApplicationManager.countJobApplications(employerName, jobName);
    }
}
