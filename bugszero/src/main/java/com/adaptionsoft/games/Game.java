package com.adaptionsoft.games;

import java.util.*;

import static com.adaptionsoft.games.Category.*;

public class Game {
    private final QuestionManager questionManager;

    ArrayList<Player> players = new ArrayList<Player>();
    int currentPlayer = 0;

    public Game() {
        questionManager = new QuestionManager();
    }

    public void addPlayer(String playerName) {
        players.add(new Player(playerName));
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        String currentPlayerName = getCurrentPlayer().getName();
        System.out.println(currentPlayerName + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!getCurrentPlayer().isInPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        if (roll % 2 != 0) {
            getCurrentPlayer().outFromPenaltyBox();
            movePlayerAndAskQuestion(roll);
        } else {
            System.out.println(currentPlayerName + " is not getting out of the penalty box");
        }

    }

    private void movePlayerAndAskQuestion(int roll) {
        getCurrentPlayer().move(roll);
        questionManager.askQuestion(getCategory(getCurrentPlayer().getPlace()));
    }

    public boolean answerCorrectly() {
        if (getCurrentPlayer().isInPenaltyBox()) {
            return true;
        }
        System.out.println("Answer was correct!!!!");
        getCurrentPlayer().increaseGoldCoin();

        return didPlayerWin();
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        getCurrentPlayer().sendToPenaltyBox();

        return true;
    }

    private boolean didPlayerWin() {
        return !(getCurrentPlayer().getGoldCoins() == 6);
    }

    void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
