package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;
import com.adaptionsoft.games.trivia.PlayerHandler;
import com.adaptionsoft.games.trivia.question.handler.QuestionHandler;

public class Game {
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
            getCurrentPlayer().move(roll);
            questionHandler.askQuestion(getCurrentPlayer().place);
            return;
        }

        playerHandler.goOutPenaltyBox(roll);
        if (getCurrentPlayer().isGettingOutOfPenaltyBox()) {
            getCurrentPlayer().move(roll);
            questionHandler.askQuestion(getCurrentPlayer().place);
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (!getCurrentPlayer().inPenaltyBox()) {
            getCurrentPlayer().answerCorrect();
            boolean winner = playerHandler.didPlayerWin();
            playerHandler.nextPlayer();

            return winner;
        }

        if (!getCurrentPlayer().isGettingOutOfPenaltyBox()) {
            playerHandler.nextPlayer();
            return true;
        }

        playerHandler.nextPlayer();
        getCurrentPlayer().answerCorrect();

        return playerHandler.didPlayerWin();
    }

    public boolean wrongAnswer() {
        return playerHandler.wrongAnswer();
    }

    private String getCurrentPlayerName() {
        return getCurrentPlayer().name;
    }

    private Player getCurrentPlayer() {
        return playerHandler.getCurrentPlayer();
    }
}
