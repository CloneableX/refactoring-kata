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
        if (jobApplication.getJob().getType() == JobType.JREQ && jobApplication.getResume().getName() == null) {
            failedJobApplications.saveJobApplication(jobApplication.getJob().getEmployer(), jobApplication.getJob(), jobApplication);
            throw new RequiresResumeForJReqJobException();
        }

        if (jobApplication.getJob().getType() == JobType.JREQ && !jobApplication.getResume().getName().equals(jobApplication.getJobSeeker().getName())) {
            throw new InvalidResumeException();
        }

        jobApplicationManager.applyJob(jobApplication.getJob().getEmployer(), jobApplication.getJob(), jobApplication.getJobSeeker(), jobApplication);
    }

    public List<Job> getJobs(String employerName) {
        return jobs.getJobs(employerName);
    }

    public List<List<String>> getJobApplications(String jobSeekerName) {
        return jobApplicationManager.getJobApplications(jobSeekerName);
    }

    public List<String> findApplicants(String jobName) {
        return findApplicants(jobName, null);
    }

    public List<String> findApplicants(String jobName, LocalDate from) {
        return findApplicants(jobName, from, null);
    }

    public List<String> findApplicants(String jobName, LocalDate from, LocalDate to) {
        if (from == null && to == null) {
            return jobApplicationManager.findJobApplicationsByJobName(jobName);
        }

        if (jobName == null && to == null) {
            return jobApplicationManager.findJobApplicationsByStartDate(from);
        }

        if (jobName == null && from == null) {
            return jobApplicationManager.findJobApplicationsByEndDate(to);
        }

        if (jobName == null) {
            return jobApplicationManager.findJobApplicationsByDateRange(from, to);
        }

        if (to != null) {
            return jobApplicationManager.findJobApplicationsByJobNameAndEndDate(jobName, to);
        }

        return jobApplicationManager.findJobApplicationsByJobNameAndStartDate(jobName, from);
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
