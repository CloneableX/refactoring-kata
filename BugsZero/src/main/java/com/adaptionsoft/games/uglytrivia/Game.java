package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    public static final String pop = "Pop";
    public static final String science = "Science";
    public static final String sports = "Sports";
    public static final String rock = "Rock";

    int playerCounter = 0;
    ArrayList<Player> players = new ArrayList<>();

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

        for (int i = 0; i < 6; i++) {
            players.add(new Player(""));
        }
    }

    public boolean isPlayable() {
        return (getPlayersSize() >= 2);
    }

    public void initPlayer(String playerName) {
        players.set(getPlayersSize(), new Player(playerName));
        playerCounter++;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + getPlayersSize());
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!getCurrentPlayer().inPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        if (roll % 2 == 0) {
            System.out.println(getCurrentPlayerName() + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
        }
        isGettingOutOfPenaltyBox = true;

        System.out.println(getCurrentPlayerName() + " is getting out of the penalty box");

        movePlayerAndAskQuestion(roll);
    }

    private void movePlayerAndAskQuestion(int roll) {
        getCurrentPlayer().move(roll);
        askQuestion();
    }

    private void askQuestion() {
        switch (getCurrentPlayer().currentCategory()) {
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

    public boolean wasCorrectlyAnswered() {
        if (!getCurrentPlayer().inPenaltyBox()) {
            getCurrentPlayer().answerCorrect();
            boolean winner = didPlayerWin();
            incrementCurrentPlayer();

            return winner;
        }

        if (!isGettingOutOfPenaltyBox) {
            incrementCurrentPlayer();
            return true;
        }

        incrementCurrentPlayer();
        System.out.println("Answer was correct!!!!");
        incrementPurse();
        System.out.println(getCurrentPlayerName()
                + " now has "
                + getCurrentPurse()
                + " Gold Coins.");

        return didPlayerWin();
    }

    public boolean wrongAnswer() {
        getCurrentPlayer().answerWrong();
        incrementCurrentPlayer();
        return true;
    }

    private boolean didPlayerWin() {
        return !(getCurrentPurse() == 6);
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

    private int getCurrentPurse() {
        return getCurrentPlayer().purse;
    }

    private void incrementPurse() {
        getCurrentPlayer().incrementPurse();
    }

    private String getCurrentPlayerName() {
        return players.get(currentPlayer).name;
    }

    private int getPlayersSize() {
        return playerCounter;
    }

    private void incrementCurrentPlayer() {
        currentPlayer++;
        if (currentPlayer == getPlayersSize()) currentPlayer = 0;
    }

    private Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }
}
