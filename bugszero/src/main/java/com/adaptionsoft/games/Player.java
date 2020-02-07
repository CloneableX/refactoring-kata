package com.adaptionsoft.games;

public class Player {
    private String name;
    private int goldCoins = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void increaseGoldCoin() {
        goldCoins++;
    }

    public int getGoldCoins() {
        return goldCoins;
    }
}
