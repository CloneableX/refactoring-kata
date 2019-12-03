package com.adaptionsoft.games.trivia.question.handler;

import com.adaptionsoft.games.trivia.question.PopQuestion;
import com.adaptionsoft.games.trivia.question.Question;

import java.util.LinkedList;

public class PopQuestionHandler {
    private LinkedList<Question> popQuestions = new LinkedList<>();

    public void createPopQuestion(int index) {
        popQuestions.addLast(new PopQuestion(index));
    }

    public void askQuestion() {
        System.out.println(popQuestions.removeFirst());
    }
}
