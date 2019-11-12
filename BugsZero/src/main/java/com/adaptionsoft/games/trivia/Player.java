package com.adaptionsoft.games.trivia;

public class Player {
    public int place = 0;
    public boolean inPenaltyBox = false;

    public Player() {
    }

    public void move(int roll) {
        place = place + roll;
        if (place > 11) place = place - 12;
    }
}
