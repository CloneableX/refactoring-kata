package com.theladders.avital.cc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JobApplication {
    private LocalDate applicationTime;
    private JobSeeker jobSeeker;
    private Resume resume;
    private Job job;

    public JobApplication(LocalDate applicationTime, JobSeeker jobSeeker, Resume resume, Job job) {
        this.applicationTime = applicationTime;
        this.jobSeeker = jobSeeker;
        this.resume = resume;
        this.job = job;
    }

    public LocalDate getApplicationTime() {
        return applicationTime;
    }

    public void isValidJobApplication() throws InvalidResumeException, RequiresResumeForJReqJobException {
        if (job.getType() == JobType.JREQ && resume.getName() == null) {
            throw new RequiresResumeForJReqJobException();
        }

        if (job.getType() == JobType.JREQ && !resume.getName().equals(jobSeeker.getName())) {
            throw new InvalidResumeException();
        }
    }

    public String getJobSeekerName() {
        return jobSeeker.getName();
    }

    public boolean isSameJobName(String jobName) {
        return getJobName().equals(jobName);
    }

    public String getEmployerName() {
        return job.getEmployerName();
    }

    public String getJobName() {
        return job.getName();
    }

    public String getJobTypeName() {
        return job.getTypeName();
    }

    public String getApplicationTime(String pattern) {
        return applicationTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public boolean isSameApplicationTime(LocalDate date) {
        return applicationTime.equals(date);
    }

    public boolean isSameEmployerName(String employerName) {
        return getEmployerName().equals(employerName);
    }

    public String toHtmlString() {
        return "<tr>"
                + "<td>"
                + getEmployerName()
                + "</td>"
                + "<td>"
                + getJobName()
                + "</td>"
                + "<td>"
                + getJobTypeName()
                + "</td>"
                + "<td>"
                + getJobSeekerName()
                + "</td>"
                + "<td>"
                + getApplicationTime("yyyy-MM-dd")
                + "</td>"
                + "</tr>";
    }

    public String toCvsString() {
        return getEmployerName()
                + ","
                + getJobName()
                + ","
                + getJobTypeName()
                + ","
                + getJobSeekerName()
                + ","
                + getApplicationTime("yyyy-MM-dd") + "\n";
    }
}
