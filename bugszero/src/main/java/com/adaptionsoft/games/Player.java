package com.adaptionsoft.games;

public class Player {
    private String name;
    private int goldCoins = 0;
    private int place = 0;
    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void increaseGoldCoin() {
        goldCoins++;
    }

    public void move(int roll) {
        place = place + roll;
        if (place > 11) place = place - 12;

        System.out.println(name
                + "'s new location is "
                + place);
    }

    public void sendToPenaltyBox() {
        System.out.println(name + " was sent to the penalty box");
        inPenaltyBox = true;
    }

    public int getGoldCoins() {
        return goldCoins;
    }

    public int getPlace() {
        return place;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void outFromPenaltyBox() {
        inPenaltyBox = false;
    }
}
