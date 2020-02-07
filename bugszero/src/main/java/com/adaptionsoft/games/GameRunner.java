
package com.adaptionsoft.games;

import java.util.Random;


public class GameRunner {

    public static void main(String[] args) {
        Random rand = new Random();
        playGame(rand);

    }

    public static void playGame(Random rand) {
        Game aGame = new Game();

        aGame.addPlayer("Chet");
        aGame.addPlayer("Pat");
        aGame.addPlayer("Sue");


        aGame.startGame(rand);
    }
}
