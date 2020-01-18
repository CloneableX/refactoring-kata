package com.adaptionsoft.games.trivia.question.handler;

public class QuestionHandler {
    private static final String pop = "Pop";
    private static final String science = "Science";
    private static final String sports = "Sports";
    private static final String rock = "Rock";

    private PopQuestionHandler popQuestionHandler = new PopQuestionHandler();
    private ScienceQuestionHandler scienceQuestionHandler = new ScienceQuestionHandler();
    private SportsQuestionHandler sportsQuestionHandler = new SportsQuestionHandler();
    private RockQuestionHandler rockQuestionHandler = new RockQuestionHandler();

    public QuestionHandler() {
        for (int i = 0; i < 50; i++) {
            popQuestionHandler.createPopQuestion(i);
            scienceQuestionHandler.createScienceQuestion(i);
            sportsQuestionHandler.createSportsQuestion(i);
            rockQuestionHandler.createRockQuestion(i);
        }
    }

    public void askQuestion(int place) {
        String category = currentCategory(place);
        System.out.println("The category is " + category);
        switch (category) {
            case QuestionHandler.pop:
                popQuestionHandler.askQuestion();
                break;
            case QuestionHandler.science:
                scienceQuestionHandler.askQuestion();
                break;
            case QuestionHandler.sports:
                sportsQuestionHandler.askQuestion();
                break;
            case QuestionHandler.rock:
                rockQuestionHandler.askQuestion();
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
