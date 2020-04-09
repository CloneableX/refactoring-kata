package tddmicroexercises.leaderboard;

public class TestData {

    public Driver driver1;
    public Driver driver2;
    public Driver driver3;
    public SelfDrivingCar driver4;

    public Race race1;
    public Race race2;
    public Race race3;
    public Race race4;
    public Race race5;
    public Race race6;

    public Leaderboard sampleLeaderboard1;
    public Leaderboard sampleLeaderboard2;

    public TestData() {
        driver1 = new Driver("Nico Rosberg", "DE");
        driver2 = new Driver("Lewis Hamilton", "UK");
        driver3 = new Driver("Sebastian Vettel", "DE");
        driver4 = new SelfDrivingCar("1.2", "Acme");

        race1 = new Race("Australian Grand Prix", driver1, driver2, driver3);
        race2 = new Race("Malaysian Grand Prix", driver3, driver2, driver1);
        race3 = new Race("Chinese Grand Prix", driver2, driver1, driver3);
        race4 = new Race("Fictional Grand Prix 1", driver1, driver2, driver4);
        race5 = new Race("Fictional Grand Prix 2", driver4, driver2, driver1);
        driver4.setAlgorithmVersion("1.3");
        race6 = new Race("Fictional Grand Prix 3", driver2, driver1, driver4);

        sampleLeaderboard1 = new Leaderboard(race1, race2, race3);
        sampleLeaderboard2 = new Leaderboard(race4, race5, race6);
    }
}
