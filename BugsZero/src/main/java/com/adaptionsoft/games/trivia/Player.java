package com.adaptionsoft.games.trivia;

public class Player {
    public int place = 0;
    private boolean inPenaltyBox = false;
    public int purse = 0;
    public String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {

    }

    public void move(int roll) {
        place = place + roll;
        if (place > 11) place = place - 12;

        System.out.println(name
                + "'s new location is "
                + place);
    }

    public void incrementPurse() {
        purse++;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void intoPenaltyBox() {
        inPenaltyBox = true;
    }

    public void answerWrong() {
        System.out.println("Question was incorrectly answered");
        System.out.println(name + " was sent to the penalty box");
        intoPenaltyBox();
    }

    public void answerCorrect() {
        incrementPurse();
        System.out.println(name
                + " now has "
                + purse
                + " Gold Coins.");
    }

}
