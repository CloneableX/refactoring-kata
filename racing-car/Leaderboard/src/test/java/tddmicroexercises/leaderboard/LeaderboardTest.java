package tddmicroexercises.leaderboard;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderboardTest {

    private TestData testData;

    @Before
    public void setUp() {
        testData = new TestData();
    }

    @Test
    public void itShouldSumThePoints() {
        // setup

        // act
        Map<String, Integer> results = testData.sampleLeaderboard1.driverResults();

        // verify
        assertTrue("results " + results, results.containsKey("Lewis Hamilton"));
        assertEquals(18 + 18 + 25, (int) results.get("Lewis Hamilton"));
    }

    @Test
    public void isShouldFindTheWinner() {
        // setup

        // act
        List<String> result = testData.sampleLeaderboard1.driverRankings();

        // verify
        assertEquals("Lewis Hamilton", result.get(0));
    }

    @Test
    public void itShouldKeepAllDriversWhenSamePoints() {
        // setup
        // bug, drops drivers with same points
        Race winDriver1 = new Race("Australian Grand Prix", testData.driver1, testData.driver2, testData.driver3);
        Race winDriver2 = new Race("Malaysian Grand Prix", testData.driver2, testData.driver1, testData.driver3);
        Leaderboard exEquoLeaderBoard = new Leaderboard(winDriver1, winDriver2);

        // act
        List<String> rankings = exEquoLeaderBoard.driverRankings();

        // verify
        assertEquals(Arrays.asList(testData.driver1.getName(), testData.driver2.getName(), testData.driver3.getName()), rankings);
        // note: the order of driver1 and driver2 is JDK/platform dependent
    }

}
