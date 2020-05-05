package com.codurance.training.tasks;

import java.io.PrintWriter;

public class Command {
    private final String label;
    private String argsLine;
    private PrintWriter out;

    public Command(String commandLine, PrintWriter out) {
        this.out = out;

        String[] commandRest = commandLine.split(" ", 2);
        this.label = commandRest[0];
        if (commandRest.length > 1) {
            this.argsLine = commandRest[1];
        }

    }

    public void execute() {
        if ("help".equals(label)) {
            help();
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }
}
