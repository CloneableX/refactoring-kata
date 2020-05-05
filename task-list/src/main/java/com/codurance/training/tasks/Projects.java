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
        projects.add(new Project(projectName));
    }

    public Project findProject(String projectName) {
        return projects.stream()
                .filter(project -> project.checkSameName(projectName))
                .findFirst()
                .orElse(null);

    }

    public void showAll() {
        for (Project project : projects) {
            out.println(project.getName());
            for (Task task : project.getTasks()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    public void setDone(int taskId, boolean done) {
        for (Project project : projects) {
            for (Task task : project.getTasks()) {
                if (task.getId() == taskId) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", taskId);
        out.println();
    }
}
