package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final BufferedReader in;
    private final PrintWriter out;

    private final Projects projects;
    private Tasks tasks;

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        projects = new Projects(out);
        tasks = new Tasks(out);
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
        Command commandTemp = new Command(commandLine, out);
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                projects.showAll();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                tasks.checkTask(commandRest[1]);
                break;
            case "uncheck":
                tasks.uncheckTask(commandRest[1]);
                break;
            case "help":
                commandTemp.execute();
                break;
            default:
                error(command);
                break;
        }
    }

    private void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            projects.addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addTask(String projectName, String description) {
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

    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

}
