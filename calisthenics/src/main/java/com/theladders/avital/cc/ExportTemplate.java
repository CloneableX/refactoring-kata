package com.theladders.avital.cc;

public enum ExportTemplate {
    CVS("Employer,Job,Job Type,Applicants,Date" + "\n" + "{CONTENT}");
    public static final String CONTENT_TAG = "{CONTENT}";
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
    private String template;

    ExportTemplate(String template) {
        this.template = template;
    }

    public String export(String content) {
        return template.replace(CONTENT_TAG, content);
    }
}
