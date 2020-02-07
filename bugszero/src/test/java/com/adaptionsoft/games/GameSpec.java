package com.adaptionsoft.games;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GameSpec {
    @Test
    public void should_not_in_penalty_box_when_roll_odd_after_answer_wrong() {
        Game game = new Game();
        game.add("Cloneable");
        game.add("Hank");

        Player playerCloneable = game.getCurrentPlayer();
        game.wrongAnswer();
        game.wasCorrectlyAnswered();
        game.roll(1);

        assertFalse(playerCloneable.isInPenaltyBox());
    }
}
