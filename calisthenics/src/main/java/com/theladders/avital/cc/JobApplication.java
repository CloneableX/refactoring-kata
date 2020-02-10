package com.theladders.avital.cc;

import java.time.LocalDate;

public class JobApplication {
    private LocalDate applicationTime;
    private JobSeeker jobSeeker;
    private Resume resume;

    public JobApplication(LocalDate applicationTime) {
        this.applicationTime = applicationTime;
    }

    public JobApplication(LocalDate applicationTime, JobSeeker jobSeeker, Resume resume) {
        this.applicationTime = applicationTime;
        this.jobSeeker = jobSeeker;
        this.resume = resume;
    }

    public LocalDate getApplicationTime() {
        return applicationTime;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public Resume getResume() {
        return resume;
    }
}
