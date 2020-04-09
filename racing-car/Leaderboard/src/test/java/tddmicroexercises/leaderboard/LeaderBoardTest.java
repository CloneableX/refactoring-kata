package tddmicroexercises.leaderboard;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeaderBoardTest {

    private TestData testData;

    @Before
    public void setUp() {
        testData = new TestData();
    }

    @Test
    public void itShouldSumThePoints() {
        // setup

        // act
        Set<Driver> results = testData.sampleLeaderBoard1.driverResults();

        Driver actualDriver = results.stream()
                .filter(driver -> driver.equals(testData.driver2))
                .findFirst()
                .orElse(null);
        // verify
        assertTrue("results " + results, results.contains(testData.driver2));
        assertEquals(18 + 18 + 25, actualDriver.getPoint());
    }

    @Test
    public void isShouldFindTheWinner() {
        // setup

        // act
        List<String> result = testData.sampleLeaderBoard1.driverRankings();

        // verify
        assertEquals("Lewis Hamilton", result.get(0));
    }

    @Test
    public void itShouldKeepAllDriversWhenSamePoints() {
        // setup
        // bug, drops drivers with same points
        Race winDriver1 = new Race("Australian Grand Prix", testData.driver1, testData.driver2, testData.driver3);
        Race winDriver2 = new Race("Malaysian Grand Prix", testData.driver2, testData.driver1, testData.driver3);
        LeaderBoard exEquoLeaderBoard = new LeaderBoard(winDriver1, winDriver2);

        // act
        List<String> rankings = exEquoLeaderBoard.driverRankings();

        // verify
        assertEquals(Arrays.asList(testData.driver2.getName(), testData.driver1.getName(), testData.driver3.getName()), rankings);
        // note: the order of driver1 and driver2 is JDK/platform dependent
    }

}
