package com.adaptionsoft.games.trivia.question.handler;

import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.question.RockQuestion;

import java.util.LinkedList;

public class RockQuestionHandler {
    private LinkedList<Question> rockQuestions = new LinkedList<>();

    public void createRockQuestion(int index) {
        rockQuestions.addLast(new RockQuestion(index));
    }

    public void askQuestion() {
        System.out.println(rockQuestions.removeFirst());
    }
}
