package com.adaptionsoft.games.trivia;

import static com.adaptionsoft.games.question.Question.*;

public class Player {
    private int place = 0;
    private boolean inPenaltyBox = false;
    public int purse = 0;
    public String name;

    public Player(String name) {
        this.name = name;
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

    public String currentCategory() {
        String category = rock;
        switch (place) {
            case 0:
            case 4:
            case 8:
                category = pop;
                break;
            case 1:
            case 5:
            case 9:
                category = science;
                break;
            case 2:
            case 6:
            case 10:
                category = sports;
                break;
        }

        System.out.println("The category is " + category);
        return category;
    }

    public boolean inPenaltyBox() {
        return inPenaltyBox;
    }

    public void intoPenaltyBox() {
        inPenaltyBox = true;
    }
}
