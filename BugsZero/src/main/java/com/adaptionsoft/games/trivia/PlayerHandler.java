package com.adaptionsoft.games.trivia;

import java.util.ArrayList;

public class PlayerHandler {
    private ArrayList<Player> players = new ArrayList<>();
    private int playerCounter = 0;
    private int currentPlayer = 0;

    public PlayerHandler() {
        for (int i = 0; i < 6; i++) {
            players.add(new Player());
        }
    }

    public void createPlayer(String playerName) {
        players.set(getPlayersSize(), new Player(playerName));
        playerCounter++;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayersSize());
    }

    public int getPlayersSize() {
        return playerCounter;
    }

    public void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == getPlayersSize()) currentPlayer = 0;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public boolean didPlayerWin() {
        return getCurrentPlayer().purse != 6;
    }

    public boolean wrongAnswer() {
        getCurrentPlayer().answerWrong();
        nextPlayer();
        return true;
    }

    public boolean goOutPenaltyBox(int roll) {
        if (roll % 2 == 0) {
            System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
            return false;
        }

        System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");
        return true;
    }

    private String getCurrentPlayerName() {
        return getCurrentPlayer().name;
    }

}
