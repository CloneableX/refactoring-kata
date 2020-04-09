package tddmicroexercises.leaderboard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RaceTest {

    private TestData testData;

    @Before
    public void setUp() {
        testData = new TestData();
    }

    @Test
    public void isShouldCalculateDriverPoints() {
        // setup

        // act

        // verify
        Assert.assertEquals(25, testData.race1.getPoints(testData.driver1));
        Assert.assertEquals(18, testData.race1.getPoints(testData.driver2));
        Assert.assertEquals(15, testData.race1.getPoints(testData.driver3));
    }

}
