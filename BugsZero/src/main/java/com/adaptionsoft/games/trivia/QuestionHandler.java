package com.adaptionsoft.games.trivia;

import java.util.LinkedList;

public class QuestionHandler {
    public static final String pop = "Pop";
    public static final String science = "Science";
    public static final String sports = "Sports";
    public static final String rock = "Rock";

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    public QuestionHandler() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(createPopQuestion(i));
            scienceQuestions.addLast(createScienceQuestion(i));
            sportsQuestions.addLast(createSportsQuestion(i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    private String createSportsQuestion(int index) {
        return "Sports Question " + index;
    }

    private String createScienceQuestion(int index) {
        return "Science Question " + index;
    }

    private String createPopQuestion(int index) {
        return "Pop Question " + index;
    }

    private String createRockQuestion(int index) {
        return "Rock Question " + index;
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
