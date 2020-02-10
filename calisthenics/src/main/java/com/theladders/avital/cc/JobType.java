package com.theladders.avital.cc;

public enum JobType {
    JREQ("JReq"), ATS("ATS");

    private String name;

    JobType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
