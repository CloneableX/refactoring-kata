package com.adaptionsoft.games.trivia.question.handler;

import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.question.SportsQuestion;

import java.util.LinkedList;

public class SportsQuestionHandler {
    private LinkedList<Question> sportsQuestions = new LinkedList<>();

    public void createSportsQuestion(int index) {
        sportsQuestions.addLast(new SportsQuestion(index));
    }

    public void askQuestion() {
        System.out.println(sportsQuestions.removeFirst());
    }
}
