package com.theladders.avital.cc;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ApplicationTest {
    Application application;

    private ArrayList<String> createNewJob(String jobName, String jobType, String employerName, String applicationTime) {
        return new ArrayList<String>() {{
            add(jobName);
            add(jobType);
            add(applicationTime);
            add(employerName);
        }};
    }

    private ArrayList<String> createNewJob(final String jobName, final String jobType) {
        return new ArrayList<String>() {{
            add(jobName);
            add(jobType);
        }};
    }


    @Before
    public void setUp() throws Exception {
        application = new Application();
    }

    @Test
    public void employers_should_be_able_to_publish_a_job() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerName = "";
        String jobName = "高级前端开发";
        publishJob(employerName, jobName, "JReq");
        List<List<String>> jobs = application.getJobs(employerName, "published");
        List<List<String>> expected = new ArrayList<List<String>>() {{
            add(createNewJob("高级前端开发", "JReq"));
        }};

        assertThat(jobs, is(expected));
    }

    @Test
    public void employers_should_only_be_able_to_see_jobs_published_by_them() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String employerTencent = "Tencent";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";
        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        publishJob(employerTencent, juniorJavaDevJob, "JReq");
        List<List<String>> jobs = application.getJobs(employerAlibaba, "published");
        List<List<String>> expected = new ArrayList<List<String>>() {{
            add(createNewJob("高级Java开发", "JReq"));
        }};

        assertThat(jobs, is(expected));
    }

    @Test
    public void employers_should_be_able_to_publish_ATS_jobs() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        List<List<String>> jobs = application.getJobs(employerAlibaba, "published");
        List<List<String>> expected = new ArrayList<List<String>>() {{
            add(createNewJob("高级Java开发", "ATS"));
        }};

        assertThat(jobs, is(expected));
    }

    @Test(expected = NotSupportedJobTypeException.class)
    public void employers_should_not_be_able_to_publish_jobs_that_are_neither_ATS_nor_JReq() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "RJeq");
    }

    @Test
    public void jobseekers_should_be_able_to_save_jobs_published_by_employers_for_later_review() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerName = "Jacky";
        String jobName = "高级Java开发";
        publishJob(employerAlibaba, jobName, "JReq");
        saveJob(jobSeekerName, jobName, "JReq");
        List<List<String>> savedJobs = application.getJobs(jobSeekerName, "published");
        List<List<String>> expected = new ArrayList<List<String>>() {{
            add(createNewJob("高级Java开发", "JReq"));
        }};

        assertThat(savedJobs, is(expected));
    }

    @Test
    public void jobseekers_should_be_able_to_apply_for_an_ATS_job_some_employer_published_without_a_resume() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerName = "Jacky";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerName, juniorJavaDevJob, "ATS", null, LocalDate.parse("2020-01-01"));
        applyJob(employerAlibaba, jobSeekerName, seniorJavaDevJob, "ATS", null, LocalDate.parse("2020-01-01"));
        List<List<String>> appliedJobs = application.getJobs(jobSeekerName, "applied");
        List<List<String>> expected = new ArrayList<List<String>>() {{
            add(createNewJob("Java开发", "ATS", "Alibaba", "2020-01-01"));
            add(createNewJob("高级Java开发", "ATS", "Alibaba", "2020-01-01"));
        }};

        assertThat(appliedJobs, is(expected));
    }

    @Test(expected = RequiresResumeForJReqJobException.class)
    public void jobseekers_should_not_be_able_to_apply_for_an_JReq_job_some_employer_published_without_a_resume() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerName = "Jacky";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        applyJob(employerAlibaba, jobSeekerName, seniorJavaDevJob, "JReq", null, LocalDate.now());
    }

    @Test(expected = InvalidResumeException.class)
    public void jobseekers_should_not_be_able_to_apply_for_an_JReq_job_some_employer_published_with_someone_else_s_resume() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerName = "Jacky";
        String seniorJavaDevJob = "高级Java开发";
        String resumeApplicantName = "Jacky Chen";

        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        applyJob(employerAlibaba, jobSeekerName, seniorJavaDevJob, "JReq", resumeApplicantName, LocalDate.now());
    }

    @Test
    public void employers_should_be_able_to_find_applicants_of_a_job() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jobSeekerLam = "Lam";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "ATS", null, LocalDate.now());
        applyJob(employerAlibaba, jobSeekerLam, seniorJavaDevJob, "ATS", null, LocalDate.now());
        List<String> applicants = application.findApplicants(seniorJavaDevJob);

        List<String> expected = new ArrayList<String>() {{
            add("Lam");
            add("Jacky");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void employers_should_be_able_to_find_applicants_to_a_job_by_application_date() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jobSeekerHo = "Ho";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerHo, seniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        List<String> applicants = application.findApplicants(null, LocalDate.parse("1999-12-20"));

        List<String> expected = new ArrayList<String>() {{
            add("Ho");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void employers_should_be_able_to_find_applicants_to_a_job_by_period_when_period_end_is_given_while_period_start_is_not() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jobSeekerHo = "Ho";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerHo, seniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        List<String> applicants = application.findApplicants(null, null, LocalDate.parse("1999-01-01"));

        List<String> expected = new ArrayList<String>() {{
            add("Jacky");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void employers_should_be_able_to_find_applicants_to_a_job_by_period() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jobSeekerHo = "Ho";
        String seniorJavaDevJob = "高级Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerHo, seniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        List<String> applicants = application.findApplicants(null, LocalDate.parse("1997-07-01"), LocalDate.parse("1999-12-20"));

        List<String> expected = new ArrayList<String>() {{
            add("Ho");
            add("Jacky");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void employers_should_be_able_to_find_applicants_to_a_job_by_job_name_and_period_when_period_start_is_given_while_period_end_is_not() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String resumeApplicantName = "Jacky";
        String jobSeekerHo = "Ho";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        applyJob(employerAlibaba, jobSeekerJacky, juniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "JReq", resumeApplicantName, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));

        List<String> applicants = application.findApplicants(seniorJavaDevJob, LocalDate.parse("1999-12-20"));

        List<String> expected = new ArrayList<String>() {{
            add("Jacky");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void employers_should_be_able_to_find_applicants_to_a_job_by_job_name_and_period_when_period_end_is_given_while_period_start_is_not() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jobSeekerHo = "Ho";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerJacky, juniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));

        List<String> applicants = application.findApplicants(juniorJavaDevJob, null, LocalDate.parse("1999-01-01"));

        List<String> expected = new ArrayList<String>() {{
            add("Jacky");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void employers_should_be_able_to_find_applicants_to_a_job_by_job_name_and_period() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerWong = "Wong";
        String jobSeekerJacky = "Jacky";
        String jobSeekerHo = "Ho";
        String jobSeekerLam = "Lam";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerWong, seniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerJacky, juniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.parse("1998-01-01"));
        applyJob(employerAlibaba, jobSeekerLam, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));

        List<String> applicants = application.findApplicants(juniorJavaDevJob, LocalDate.parse("1997-01-01"), LocalDate.parse("1999-01-01"));

        List<String> expected = new ArrayList<String>() {{
            add("Ho");
            add("Jacky");
        }};

        assertThat(applicants, is(expected));
    }

    @Test
    public void should_generator_csv_reports_of_all_jobseekers_on_a_given_date() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jackyResume = "Jacky";
        String jobSeekerHo = "Ho";
        String jobSeekerLam = "Lam";
        String lamResume = "Lam";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        applyJob(employerAlibaba, jobSeekerJacky, juniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "JReq", jackyResume, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerLam, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerLam, seniorJavaDevJob, "JReq", lamResume, LocalDate.parse("1999-12-20"));

        String csv = application.export("csv", LocalDate.parse("1999-12-20"));
        String expected = "Employer,Job,Job Type,Applicants,Date" + "\n" + "Alibaba,Java开发,ATS,Ho,1999-12-20" + "\n" + "Alibaba,Java开发,ATS,Lam,1999-12-20" + "\n" + "Alibaba,高级Java开发,JReq,Lam,1999-12-20" + "\n" + "Alibaba,高级Java开发,JReq,Jacky,1999-12-20" + "\n";

        assertThat(csv, is(expected));
    }

    @Test
    public void should_generator_html_reports_of_all_jobseekers_on_a_given_date() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jackyResume = "Jacky";
        String jobSeekerHo = "Ho";
        String jobSeekerLam = "Lam";
        String lamResume = "Lam";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        applyJob(employerAlibaba, jobSeekerJacky, juniorJavaDevJob, "ATS", null, LocalDate.parse("1997-07-01"));
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "JReq", jackyResume, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerLam, juniorJavaDevJob, "ATS", null, LocalDate.parse("1999-12-20"));
        applyJob(employerAlibaba, jobSeekerLam, seniorJavaDevJob, "JReq", lamResume, LocalDate.parse("1999-12-20"));

        String csv = application.export("html", LocalDate.parse("1999-12-20"));
        String expected = "<!DOCTYPE html>"
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
                + "<tr>"
                + "<td>Alibaba</td>"
                + "<td>Java开发</td>"
                + "<td>ATS</td>"
                + "<td>Ho</td>"
                + "<td>1999-12-20</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Alibaba</td>"
                + "<td>Java开发</td>"
                + "<td>ATS</td>"
                + "<td>Lam</td>"
                + "<td>1999-12-20</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Alibaba</td>"
                + "<td>高级Java开发</td>"
                + "<td>JReq</td>"
                + "<td>Lam</td>"
                + "<td>1999-12-20</td>"
                + "</tr>"
                + "<tr>"
                + "<td>Alibaba</td>"
                + "<td>高级Java开发</td>"
                + "<td>JReq</td>"
                + "<td>Jacky</td>"
                + "<td>1999-12-20</td>"
                + "</tr>"
                + "</tbody>"
                + "</table>"
                + "</body>"
                + "</html>";

        assertThat(csv, is(expected));
    }

    @Test
    public void should_be_able_to_see_successful_application_of_a_job_for_an_employer() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String employerTencent = "Tencent";
        String jobSeekerJacky = "Jacky";
        String jobSeekerHo = "Ho";
        String jobSeekerLam = "Lam";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "ATS");
        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        publishJob(employerTencent, juniorJavaDevJob, "ATS");
        applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "ATS", null, LocalDate.now());
        applyJob(employerAlibaba, jobSeekerLam, seniorJavaDevJob, "ATS", null, LocalDate.now());
        applyJob(employerAlibaba, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.now());
        applyJob(employerTencent, jobSeekerHo, juniorJavaDevJob, "ATS", null, LocalDate.now());

        assertThat(application.getSuccessfulApplications(employerAlibaba, seniorJavaDevJob), is(2));
        assertThat(application.getSuccessfulApplications(employerAlibaba, juniorJavaDevJob), is(1));
    }

    @Test
    public void should_be_able_to_see_unsuccessful_applications_of_a_job_for_an_employer() throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        String employerAlibaba = "Alibaba";
        String jobSeekerJacky = "Jacky";
        String jobSeekerLam = "Lam";
        String seniorJavaDevJob = "高级Java开发";
        String juniorJavaDevJob = "Java开发";

        publishJob(employerAlibaba, seniorJavaDevJob, "JReq");
        publishJob(employerAlibaba, juniorJavaDevJob, "ATS");
        try {
            applyJob(employerAlibaba, jobSeekerJacky, seniorJavaDevJob, "JReq", null, LocalDate.now());
        } catch (RequiresResumeForJReqJobException ignored) {
        }
        applyJob(employerAlibaba, jobSeekerLam, juniorJavaDevJob, "ATS", null, LocalDate.now());

        assertThat(application.getUnsuccessfulApplications(employerAlibaba, seniorJavaDevJob), is(1));
        assertThat(application.getUnsuccessfulApplications(employerAlibaba, juniorJavaDevJob), is(0));
    }

    private void publishJob(String employerName, String jobName, String jobType) throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        application.execute(new Employer(employerName), new Job(jobName, jobType), new JobSeeker(null), new Resume(null), new JobApplication(null), Command.PUBLISH);
    }

    private void saveJob(String jobSeekerName, String jobName, String jobType) throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        application.execute(new Employer(jobSeekerName), new Job(jobName, jobType), new JobSeeker(null), new Resume(null), new JobApplication(null), Command.SAVE);
    }

    private void applyJob(String employer,
                          String jobSeekerName,
                          String jobName,
                          String jobType,
                          String resumeName,
                          LocalDate applicationDate) throws NotSupportedJobTypeException, RequiresResumeForJReqJobException, InvalidResumeException {
        application.execute(
                new Employer(employer),
                new Job(jobName, jobType),
                new JobSeeker(jobSeekerName),
                new Resume(resumeName),
                new JobApplication(applicationDate),
                Command.APPLY);
    }
}
