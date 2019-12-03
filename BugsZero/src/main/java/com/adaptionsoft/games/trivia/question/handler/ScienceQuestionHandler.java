package com.adaptionsoft.games.trivia.question.handler;

import com.adaptionsoft.games.trivia.question.Question;
import com.adaptionsoft.games.trivia.question.ScienceQuestion;

import java.util.LinkedList;

public class ScienceQuestionHandler {
    private LinkedList<Question> scienceQuestions = new LinkedList<>();

    public void createScienceQuestion(int index) {
        scienceQuestions.addLast(new ScienceQuestion(index));
    }

    public void askQuestion() {
        System.out.println(scienceQuestions.removeFirst());
    }
}
