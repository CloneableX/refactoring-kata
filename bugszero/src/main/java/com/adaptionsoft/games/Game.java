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

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (isCurrentInPenaltyBox()) {
            if (roll % 2 != 0) {
                getCurrentPlayer().outFromPenaltyBox();

                System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
                movePlayerAndAskQuestion(roll);
            } else {
                System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
            }

        } else {

            movePlayerAndAskQuestion(roll);
        }

    }

    private void movePlayerAndAskQuestion(int roll) {
        getCurrentPlayer().move(roll);
        questionManager.askQuestion(getCategory(getCurrentPlace()));
    }

    public boolean wasCorrectlyAnswered() {
        if (!isCurrentInPenaltyBox()) {
            System.out.println("Answer was correct!!!!");
            getCurrentPlayer().increaseGoldCoin();

            boolean winner = didPlayerWin();
            nextPlayer();

            return winner;
        }

        nextPlayer();
        return true;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        getCurrentPlayer().sendToPenaltyBox();

        nextPlayer();
        return true;
    }

    private boolean didPlayerWin() {
        return !(getCurrentPurses() == 6);
    }

    private boolean isCurrentInPenaltyBox() {
        return getCurrentPlayer().isInPenaltyBox();
    }

    private int getCurrentPurses() {
        return getCurrentPlayer().getGoldCoins();
    }

    private int getCurrentPlace() {
        return getCurrentPlayer().getPlace();
    }

    private String getCurrentPlayerName() {
        return getCurrentPlayer().getName();
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
