package com.theladders.avital.cc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.theladders.avital.cc.Command.*;
import static java.util.Map.*;

public class Application {
    private final HashMap<String, List<List<String>>> applied = new HashMap<>();
    private final List<List<String>> failedApplications = new ArrayList<>();
    private JobManager jobManager = new JobManager();
    private JobApplicationManager jobApplicationManager = new JobApplicationManager();

    public void execute(Employer employer,
                        Job job,
                        JobSeeker jobSeeker,
                        Resume resume,
                        JobApplication jobApplication,
                        Command command) throws EmptyJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        if (command == PUBLISH) {
            jobManager.publishJob(employer, job);
            return;
        }

        if (command == SAVE) {
            jobManager.saveJob(employer, job);
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
            List<String> failedApplication = new ArrayList<>() {{
                add(job.getName());
                add(job.getTypeName());
                add(jobApplication.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                add(employer.getName());
            }};
            failedApplications.add(failedApplication);
            throw new RequiresResumeForJReqJobException();
        }

        if (job.getType() == JobType.JREQ && !resume.getName().equals(jobSeeker.getName())) {
            throw new InvalidResumeException();
        }

        saveJobApplication(employer, job, jobSeeker, jobApplication);
    }

    private void saveJobApplication(Employer employer, Job job, JobSeeker jobSeeker, JobApplication jobApplication) {
        List<List<String>> saved = this.applied.getOrDefault(jobSeeker.getName(), new ArrayList<>());

        saved.add(new ArrayList<>() {{
            add(job.getName());
            add(job.getTypeName());
            add(jobApplication.getApplicationTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            add(employer.getName());
        }});
        applied.put(jobSeeker.getName(), saved);
        jobApplicationManager.applyJob(employer, job, jobSeeker, jobApplication);
    }

    public List<List<String>> getJobs(String employerName) {
        return jobManager.getJobs(employerName);
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
            result = buildCvsContent(date, result);
            return result;
        }

        return jobApplicationManager.exportHtml(date);
    }

    private String buildCvsContent(LocalDate date, String result) {
        for (Entry<String, List<List<String>>> set : this.applied.entrySet()) {
            String applicant = set.getKey();
            List<List<String>> jobs1 = set.getValue();
            List<List<String>> appliedOnDate = jobs1.stream().filter(job -> job.get(2).equals(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))).collect(Collectors.toList());

            result = buildCvsItem(result, applicant, appliedOnDate);
        }
        return result;
    }

    private String buildCvsItem(String result, String applicant, List<List<String>> appliedOnDate) {
        for (List<String> job : appliedOnDate) {
            result = result.concat(job.get(3) + "," + job.get(0) + "," + job.get(1) + "," + applicant + "," + job.get(2) + "\n");
        }
        return result;
    }

    public int getSuccessfulApplications(String employerName, String jobName) {
        int result = 0;
        for (Entry<String, List<List<String>>> set : this.applied.entrySet()) {
            List<List<String>> jobs = set.getValue();

            result += jobs.stream().anyMatch(job -> job.get(3).equals(employerName) && job.get(0).equals(jobName)) ? 1 : 0;
        }
        return result;
    }

    public int getUnsuccessfulApplications(String employerName, String jobName) {
        return (int) failedApplications.stream().filter(job -> job.get(0).equals(jobName) && job.get(3).equals(employerName)).count();
    }
}
