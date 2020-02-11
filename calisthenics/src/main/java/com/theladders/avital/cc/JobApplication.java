package com.theladders.avital.cc;

import java.time.LocalDate;

public class JobApplication {
    private LocalDate applicationTime;
    private JobSeeker jobSeeker;
    private Resume resume;
    private Job job;

    public JobApplication(LocalDate applicationTime) {
        this.applicationTime = applicationTime;
    }

    public JobApplication(LocalDate applicationTime, JobSeeker jobSeeker, Resume resume, Job job) {
        this.applicationTime = applicationTime;
        this.jobSeeker = jobSeeker;
        this.resume = resume;
        this.job = job;
    }

    public LocalDate getApplicationTime() {
        return applicationTime;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public Job getJob() {
        return job;
    }

    public void isValidJobApplication() throws InvalidResumeException, RequiresResumeForJReqJobException {
        if (job.getType() == JobType.JREQ && resume.getName() == null) {
            throw new RequiresResumeForJReqJobException();
        }

        if (job.getType() == JobType.JREQ && !resume.getName().equals(getJobSeeker().getName())) {
            throw new InvalidResumeException();
        }
    }

    public String getJobSeekerName() {
        return jobSeeker.getName();
    }
}
