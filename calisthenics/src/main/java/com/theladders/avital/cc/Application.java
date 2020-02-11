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
            failedJobApplications.saveJobApplication(jobApplication.getJob().getEmployer(), jobApplication.getJob(), jobApplication);
            throw exception;
        }

        jobApplicationManager.applyJob(jobApplication);
    }

    public List<Job> getJobs(String employerName) {
        return jobs.getJobs(employerName);
    }

    public List<List<String>> getJobApplications(String jobSeekerName) {
        return jobApplicationManager.getJobApplications(jobSeekerName);
    }

    public List<String> findApplicants(String jobName, DateRange dateRange) {
        if (dateRange.getFrom() == null && dateRange.getTo() == null) {
            return jobApplicationManager.findJobApplicationsByJobName(jobName);
        }

        if (jobName == null && dateRange.getTo() == null) {
            return jobApplicationManager.findJobApplicationsByStartDate(dateRange.getFrom());
        }

        if (jobName == null && dateRange.getFrom() == null) {
            return jobApplicationManager.findJobApplicationsByEndDate(dateRange.getTo());
        }

        if (jobName == null) {
            return jobApplicationManager.findJobApplicationsByDateRange(dateRange.getFrom(), dateRange.getTo());
        }

        if (dateRange.getTo() != null) {
            return jobApplicationManager.findJobApplicationsByJobNameAndEndDate(jobName, dateRange.getTo());
        }

        return jobApplicationManager.findJobApplicationsByJobNameAndStartDate(jobName, dateRange.getFrom());
    }

    public String exportCvs(LocalDate date) {
        String result = "Employer,Job,Job Type,Applicants,Date" + "\n";
        result = jobApplicationManager.buildCvsContent(date, result);
        return result;
    }

    public String exportHtml(LocalDate date) {
        return jobApplicationManager.exportHtml(date);
    }

    public int getUnsuccessfulApplications(String employerName, String jobName) {
        return failedJobApplications.getUnsuccessfulApplications(employerName, jobName);
    }

    public int getSuccessfulApplications(String employerName, String jobName) {
        return jobApplicationManager.getSuccessfulApplications(employerName, jobName);
    }
}
