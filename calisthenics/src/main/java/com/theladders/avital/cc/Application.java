package com.theladders.avital.cc;

import java.time.LocalDate;
import java.util.*;

import static com.theladders.avital.cc.Command.*;

public class Application {
    private Jobs jobs = new Jobs();
    private JobApplicationManager jobApplicationManager = new JobApplicationManager();
    private FailedJobApplications failedJobApplications = new FailedJobApplications();

    public void execute(Employer employer,
                        Job job,
                        JobSeeker jobSeeker,
                        Resume resume,
                        JobApplication jobApplication,
                        Command command) throws EmptyJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        if (command == PUBLISH) {
            jobs.publishJob(employer, job);
            return;
        }

        if (command == SAVE) {
            jobs.saveJob(employer, job);
            return;
        }

        if (command == APPLY) {
            applyJob(employer, job, jobSeeker, resume, jobApplication);
        }
    }

    private void applyJob(Employer employer,
                          Job job,
                          JobSeeker jobSeeker,
                          Resume resume,
                          JobApplication jobApplication) throws RequiresResumeForJReqJobException, InvalidResumeException {
        if (job.getType() == JobType.JREQ && resume.getName() == null) {
            failedJobApplications.saveJobApplication(employer, job, jobApplication);
            throw new RequiresResumeForJReqJobException();
        }

        if (job.getType() == JobType.JREQ && !resume.getName().equals(jobSeeker.getName())) {
            throw new InvalidResumeException();
        }

        jobApplicationManager.applyJob(employer, job, jobSeeker, jobApplication);
    }

    public List<List<String>> getJobs(String employerName) {
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

    public String export(String type, LocalDate date) {
        if (type.equals("csv")) {
            String result = "Employer,Job,Job Type,Applicants,Date" + "\n";
            result = jobApplicationManager.buildCvsContent(date, result);
            return result;
        }

        return jobApplicationManager.exportHtml(date);
    }

    public int getUnsuccessfulApplications(String employerName, String jobName) {
        return failedJobApplications.getUnsuccessfulApplications(employerName, jobName);
    }

    public int getSuccessfulApplications(String employerName, String jobName) {
        return jobApplicationManager.getSuccessfulApplications(employerName, jobName);
    }
}
