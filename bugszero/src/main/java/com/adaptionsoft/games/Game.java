package com.adaptionsoft.games;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    ArrayList<Player> players = new ArrayList<Player>();

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public void add(String playerName) {
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
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory().equals(POP))
            System.out.println(popQuestions.removeFirst());
        if (currentCategory().equals(SCIENCE))
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory().equals(SPORTS))
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory().equals(ROCK))
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (getCurrentPlace() == 0) return POP;
        if (getCurrentPlace() == 4) return POP;
        if (getCurrentPlace() == 8) return POP;
        if (getCurrentPlace() == 1) return SCIENCE;
        if (getCurrentPlace() == 5) return SCIENCE;
        if (getCurrentPlace() == 9) return SCIENCE;
        if (getCurrentPlace() == 2) return SPORTS;
        if (getCurrentPlace() == 6) return SPORTS;
        if (getCurrentPlace() == 10) return SPORTS;
        return ROCK;
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
