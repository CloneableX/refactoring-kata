package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private final String pop = "Pop";
    private final String science = "Science";
    private final String sports = "Sports";
    private final String rock = "Rock";

    ArrayList<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList<String> popQuestions = new LinkedList<String>();
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

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public void initPlayer(String playerName) {


        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!inPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        if (roll % 2 == 0) {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
        }
        isGettingOutOfPenaltyBox = true;

        System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        movePlayerAndAskQuestion(roll);
    }

    private void movePlayerAndAskQuestion(int roll) {
        places[currentPlayer] = places[currentPlayer] + roll;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        String currentCategory = currentCategory();
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
        int place = places[currentPlayer];
        switch (place) {
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
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
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
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        return didPlayerWin();
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        initCurrentPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    private boolean inPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    private void initCurrentPlayer() {
        if (currentPlayer == players.size()) currentPlayer = 0;
    }
}
