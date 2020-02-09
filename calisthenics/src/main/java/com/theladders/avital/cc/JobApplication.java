package com.theladders.avital.cc;

import java.time.LocalDate;

public class JobApplication {
    private LocalDate applicationTime;

    public JobApplication(LocalDate applicationTime) {
        this.applicationTime = applicationTime;
    }

    public LocalDate getApplicationTime() {
        return applicationTime;
    }
}
