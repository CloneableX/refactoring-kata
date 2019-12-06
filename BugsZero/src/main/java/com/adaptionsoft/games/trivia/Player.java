package com.adaptionsoft.games.trivia;

public class Player {
    public int place = 0;
    private boolean inPenaltyBox = false;
    int purse = 0;
    public String name;

    Player(String name) {
        this.name = name;
    }

    Player() {

    }

    public void move(int roll) {
        place = place + roll;
        if (place > 11) place = place - 12;

        System.out.println(name
                + "'s new location is "
                + place);
    }

    private void incrementPurse() {
        purse++;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    private void intoPenaltyBox() {
        inPenaltyBox = true;
    }

    void answerWrong() {
        System.out.println("Question was incorrectly answered");
        System.out.println(name + " was sent to the penalty box");
        intoPenaltyBox();
    }

    public void answerCorrect() {
        System.out.println("Answer was correct!!!!");
        incrementPurse();
        System.out.println(name
                + " now has "
                + purse
                + " Gold Coins.");
    }

}
