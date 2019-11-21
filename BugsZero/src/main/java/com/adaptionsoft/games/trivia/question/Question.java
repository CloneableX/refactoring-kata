package com.adaptionsoft.games.trivia.question;

public class Question {
    private String category;
    private int index;

    public Question(String category, int index) {
        this.category = category;
        this.index = index;
    }

    @Override
    public String toString() {
        return category + " Question " + index;
    }
}
