package com.theladders.avital.cc;

public enum ExportTemplate {
    ;
    public static final String CONTENT_TAG = "{CONTENT}";
    public static final String CVS_TEMPLATE = "Employer,Job,Job Type,Applicants,Date" + "\n" + CONTENT_TAG;
    public static final String HTML_TEMPLATE = "<!DOCTYPE html>"
            + "<body>"
            + "<table>"
            + "<thead>"
            + "<tr>"
            + "<th>Employer</th>"
            + "<th>Job</th>"
            + "<th>Job Type</th>"
            + "<th>Applicants</th>"
            + "<th>Date</th>"
            + "</tr>"
            + "</thead>"
            + "<tbody>"
            + CONTENT_TAG
            + "</tbody>"
            + "</table>"
            + "</body>"
            + "</html>";
}
