package com.adaptionsoft.games.trivia.question;

public class Question {
    protected String category;
    protected int index;

    public Question(String category, int index) {
        this.category = category;
        this.index = index;
    }

    @Override
    public String toString() {
        return category + " Question " + index;
    }
}
