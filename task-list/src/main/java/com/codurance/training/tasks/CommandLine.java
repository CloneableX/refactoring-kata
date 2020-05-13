package com.codurance.training.tasks;

import java.io.PrintWriter;

public class CommandLine {
    private final String command;
    private String argsAsString;
    private PrintWriter out;

    public CommandLine(String commandLine, PrintWriter out) {
        this.out = out;

        String[] commandRest = commandLine.split(" ", 2);
        this.command = commandRest[0];
        if (commandRest.length > 1) {
            this.argsAsString = commandRest[1];
        }
    }

    public void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }

    public void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

    public String getArgsAsString() {
        return this.argsAsString;
    }

    public void showAllProjects(Projects projects) {
        projects.showAll();
    }



    public void checkTask(Tasks tasks, String argsAsString) {
        tasks.checkTask(argsAsString);
    }

    public void uncheckTask(Tasks tasks, String argsAsString) {
        tasks.uncheckTask(argsAsString);
    }

    public void add(String commandLine, Projects projects, Tasks tasks) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            projects.addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1], projects, tasks);
        }
    }

    private void addTask(String projectName, String description, Projects projects, Tasks tasks) {
        Project project = projects.findProject(projectName);
        if (project == null) {
            out.printf("Could not find a project with the name \"%s\".", projectName);
            out.println();
            return;
        }
        Task task = new Task(description, out);
        project.addTask(task);
        tasks.addTask(task);
    }

    public boolean execute(Projects projects, Tasks tasks) {
        switch (this.command) {
            case "show":
                showAllProjects(projects);
                return false;
            case "add":
                add(this.argsAsString, projects, tasks);
                return false;
            case "check":
                checkTask(tasks, this.argsAsString);
                return false;
            case "uncheck":
                uncheckTask(tasks, this.argsAsString);
                return false;
            case "help":
                help();
                return false;
            case "quit":
                return true;
            default:
                error(this.command);
                return false;
        }
    }
}
