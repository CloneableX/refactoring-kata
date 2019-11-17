package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.Player;
import com.adaptionsoft.games.trivia.PlayerHandler;

import java.util.LinkedList;

public class Game {
    public static final String pop = "Pop";
    public static final String science = "Science";
    public static final String sports = "Sports";
    public static final String rock = "Rock";

    LinkedList<String> popQuestions = new LinkedList<>();
    LinkedList<String> scienceQuestions = new LinkedList<>();
    LinkedList<String> sportsQuestions = new LinkedList<>();
    LinkedList<String> rockQuestions = new LinkedList<>();

    boolean isGettingOutOfPenaltyBox;
    private PlayerHandler playerHandler;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(createPopQuestion(i));
            scienceQuestions.addLast(createScienceQuestion(i));
            sportsQuestions.addLast(createSportsQuestion(i));
            rockQuestions.addLast(createRockQuestion(i));
        }


        playerHandler = new PlayerHandler();
    }

    public boolean isPlayable() {
        return (playerHandler.getPlayersSize() >= 2);
    }

    public void initPlayer(String playerName) {
        playerHandler.createPlayer(playerName);
    }

    public void roll(int roll) {
        System.out.println(getCurrentPlayerName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (!getCurrentPlayer().inPenaltyBox()) {
            movePlayerAndAskQuestion(roll);
            return;
        }

        goOutPenaltyBox(roll);
    }

    private void goOutPenaltyBox(int roll) {
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
            System.out.println("Answer was corrent!!!!");
            getCurrentPlayer().answerCorrect();
            boolean winner = playerHandler.didPlayerWin();
            playerHandler.nextPlayer();

            return winner;
        }

        if (!isGettingOutOfPenaltyBox) {
            playerHandler.nextPlayer();
            return true;
        }

        playerHandler.nextPlayer();
        System.out.println("Answer was correct!!!!");
        getCurrentPlayer().answerCorrect();

        return playerHandler.didPlayerWin();
    }

    public boolean wrongAnswer() {
        getCurrentPlayer().answerWrong();
        playerHandler.nextPlayer();
        return true;
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

    private String getCurrentPlayerName() {
        return getCurrentPlayer().name;
    }

    private Player getCurrentPlayer() {
        return playerHandler.getCurrentPlayer();
    }
}
