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
        questionHandler.askQuestion(getCurrentPlayer().place);
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

    private String getCurrentPlayerName() {
        return getCurrentPlayer().name;
    }

    private Player getCurrentPlayer() {
        return playerHandler.getCurrentPlayer();
    }
}
