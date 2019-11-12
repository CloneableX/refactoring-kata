package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private final String pop = "Pop";
    private final String science = "Science";
    private final String sports = "Sports";
    private final String rock = "Rock";

    ArrayList<String> players = new ArrayList<>();
    Player[] newPlayers = new Player[6];

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(createPopQuestion(i));
            scienceQuestions.addLast(createScienceQuestion(i));
            sportsQuestions.addLast(createSportsQuestion(i));
            rockQuestions.addLast(createRockQuestion(i));
        }

        for (int i = 0; i < newPlayers.length; i++) {
            newPlayers[i] = new Player();
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void initPlayer(String playerName) {
        players.add(playerName);
        newPlayers[howManyPlayers()] = new Player();

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayerSize());
    }

    public int howManyPlayers() {
        return getPlayerSize();
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayer() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!inPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        if (roll % 2 == 0) {
            System.out.println(getCurrentPlayer() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
        }
        isGettingOutOfPenaltyBox = true;

        System.out.println(getCurrentPlayer() + " is getting out of the penalty box");
        movePlayerAndAskQuestion(roll);
    }

    private void movePlayerAndAskQuestion(int roll) {
        movePlayer(roll);
        askQuestion();
    }

    private void movePlayer(int roll) {
        newPlayers[currentPlayer].move(roll);
        System.out.println(getCurrentPlayer()
                + "'s new location is "
                + getCurrentPlace());
    }

    private void askQuestion() {
        String currentCategory = currentCategory();
        System.out.println("The category is " + currentCategory);
        switch (currentCategory) {
            case pop:
                System.out.println(popQuestions.removeFirst());
                break;
            case science:
                System.out.println(scienceQuestions.removeFirst());
                break;
            case sports:
                System.out.println(sportsQuestions.removeFirst());
                break;
            case rock:
                System.out.println(rockQuestions.removeFirst());
        }
    }


    private String currentCategory() {
        switch (getCurrentPlace()) {
            case 0:
            case 4:
            case 8:
                return pop;
            case 1:
            case 5:
            case 9:
                return science;
            case 2:
            case 6:
            case 10:
                return sports;
            default:
                return rock;
        }
    }

    public boolean wasCorrectlyAnswered() {
        if (!inPenaltyBox()) {
            System.out.println("Answer was corrent!!!!");
            incrementPurse();
            System.out.println(getCurrentPlayer()
                    + " now has "
                    + getCurrentPurse()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            initCurrentPlayer();

            return winner;
        }

        if (!isGettingOutOfPenaltyBox) {
            currentPlayer++;
            initCurrentPlayer();
            return true;
        }

        System.out.println("Answer was correct!!!!");
        currentPlayer++;
        initCurrentPlayer();
        incrementPurse();
        System.out.println(getCurrentPlayer()
                + " now has "
                + getCurrentPurse()
                + " Gold Coins.");

        return didPlayerWin();
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(getCurrentPlayer() + " was sent to the penalty box");
        newPlayers[currentPlayer].inPenaltyBox = true;

        currentPlayer++;
        initCurrentPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(getCurrentPurse() == 6);
    }

    private void initCurrentPlayer() {
        if (currentPlayer == getPlayerSize()) currentPlayer = 0;
    }

    private String createSportsQuestion(int index) {
        return "Sports Question " + index;
    }

    private String createScienceQuestion(int index) {
        return "Science Question " + index;
    }

    private String createPopQuestion(int index) {
        return "Pop Question " + index;
    }

    private String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    private int getCurrentPlace() {
        return newPlayers[currentPlayer].place;
    }

    private boolean inPenaltyBox() {
        return newPlayers[currentPlayer].inPenaltyBox;
    }

    private int getCurrentPurse() {
        return newPlayers[currentPlayer].purse;
    }

    private void incrementPurse() {
        newPlayers[currentPlayer].purse = getCurrentPurse() + 1;
    }

    private String getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    private int getPlayerSize() {
        return players.size();
    }
}
