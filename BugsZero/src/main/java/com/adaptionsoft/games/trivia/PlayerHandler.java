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
}
