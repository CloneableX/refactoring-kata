package com.codurance.training.tasks;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Projects {
    private List<Project> projects = new ArrayList<>();
    private PrintWriter out;

    public Projects(PrintWriter out) {
        this.out = out;
    }

    public void addProject(String projectName) {
        projects.add(new Project(projectName, out));
    }

    public Project findProject(String projectName) {
        return projects.stream()
                .filter(project -> project.sameName(projectName))
                .findFirst()
                .orElse(null);

    }

    public void showAll() {
        projects.forEach(Project::formatPrint);
    }

}
