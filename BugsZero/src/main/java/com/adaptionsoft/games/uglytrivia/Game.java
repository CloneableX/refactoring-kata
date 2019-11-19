package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;
import com.adaptionsoft.games.trivia.PlayerHandler;
import com.adaptionsoft.games.trivia.QuestionHandler;

public class Game {
    boolean isGettingOutOfPenaltyBox;
    private PlayerHandler playerHandler;
    private QuestionHandler questionHandler;

    public Game() {
        questionHandler = new QuestionHandler();
        playerHandler = new PlayerHandler();
    }

    public boolean isPlayable() {
        return (playerHandler.getPlayersSize() >= 2);
    }

    public void initPlayer(String playerName) {
        playerHandler.createPlayer(playerName);
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!getCurrentPlayer().inPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        goOutPenaltyBox(roll);
    }

    private void goOutPenaltyBox(int roll) {
        if (roll % 2 == 0) {
            System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
        }

        isGettingOutOfPenaltyBox = true;
        System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
        movePlayerAndAskQuestion(roll);
    }

    private void movePlayerAndAskQuestion(int roll) {
        getCurrentPlayer().move(roll);
        askQuestion();
    }

    private void askQuestion() {
        String category = currentCategory(getCurrentPlayer().place);
        System.out.println("The category is " + category);
        switch (category) {
            case QuestionHandler.pop:
                questionHandler.askPopQuestion();
                break;
            case QuestionHandler.science:
                questionHandler.askScienceQuestion();
                break;
            case QuestionHandler.sports:
                questionHandler.askSportsQuestion();
                break;
            case QuestionHandler.rock:
                questionHandler.askRockQuestion();
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

    public boolean wasCorrectlyAnswered() {
        if (!getCurrentPlayer().inPenaltyBox()) {
            System.out.println("Answer was corrent!!!!");
            getCurrentPlayer().answerCorrect();
            boolean winner = playerHandler.didPlayerWin();
            playerHandler.nextPlayer();

            return winner;
        }

        if (!isGettingOutOfPenaltyBox) {
            playerHandler.nextPlayer();
            return true;
        }

        playerHandler.nextPlayer();
        System.out.println("Answer was correct!!!!");
        getCurrentPlayer().answerCorrect();

        return playerHandler.didPlayerWin();
    }

    public boolean wrongAnswer() {
        getCurrentPlayer().answerWrong();
        playerHandler.nextPlayer();
        return true;
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

    private String getCurrentPlayerName() {
        return getCurrentPlayer().name;
    }

    private Player getCurrentPlayer() {
        return playerHandler.getCurrentPlayer();
    }
}
