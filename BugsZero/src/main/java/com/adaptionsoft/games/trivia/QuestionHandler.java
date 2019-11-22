package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.question.*;

import java.util.LinkedList;

public class QuestionHandler {
    private static final String pop = "Pop";
    private static final String science = "Science";
    private static final String sports = "Sports";
    private static final String rock = "Rock";

    private LinkedList<Question> popQuestions = new LinkedList<>();
    private LinkedList<Question> scienceQuestions = new LinkedList<>();
    private LinkedList<Question> sportsQuestions = new LinkedList<>();
    private LinkedList<Question> rockQuestions = new LinkedList<>();

    public QuestionHandler() {
        for (int i = 0; i < 50; i++) {
            createPopQuestion(i);
            createScienceQuestion(i);
            createSportsQuestion(i);
            createRockQuestion(i);
        }
    }

    private void createSportsQuestion(int index) {
        sportsQuestions.addLast(new SportsQuestion(index));
    }

    private void createScienceQuestion(int index) {
        scienceQuestions.addLast(new ScienceQuestion(index));
    }

    private void createPopQuestion(int index) {
        popQuestions.addLast(new PopQuestion(index));
    }

    private void createRockQuestion(int index) {
        rockQuestions.addLast(new RockQuestion(index));
    }

    private void askPopQuestion() {
        System.out.println(popQuestions.removeFirst());
    }

    private void askScienceQuestion() {
        System.out.println(scienceQuestions.removeFirst());
    }

    private void askSportsQuestion() {
        System.out.println(sportsQuestions.removeFirst());
    }

    private void askRockQuestion() {
        System.out.println(rockQuestions.removeFirst());
    }

    public void askQuestion(int place) {
        String category = currentCategory(place);
        System.out.println("The category is " + category);
        switch (category) {
            case QuestionHandler.pop:
                askPopQuestion();
                break;
            case QuestionHandler.science:
                askScienceQuestion();
                break;
            case QuestionHandler.sports:
                askSportsQuestion();
                break;
            case QuestionHandler.rock:
                askRockQuestion();
        }

    }

    private String currentCategory(int place) {
        switch (place) {
            case 0:
            case 4:
            case 8:
                return QuestionHandler.pop;
            case 1:
            case 5:
            case 9:
                return QuestionHandler.science;
            case 2:
            case 6:
            case 10:
                return QuestionHandler.sports;
            default:
                return QuestionHandler.rock;
        }
    }
}
