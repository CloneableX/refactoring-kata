package com.adaptionsoft.games.trivia;

public class Player {
    public int place = 0;
    public boolean inPenaltyBox = false;
    public int purse = 0;
    public String name;

    public Player(String name) {
        this.name = name;
    }

    public void move(int roll) {
        place = place + roll;
        if (place > 11) place = place - 12;
    }
}
