package com.adaptionsoft.games.trivia.question;

public class Question {
    private String category;

    public Question(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}
