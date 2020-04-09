package tddmicroexercises.leaderboard;

import java.util.Arrays;
import java.util.List;

public class Race {

    private static final Integer[] POINTS = new Integer[]{25, 18, 15};

    private final String name;
    private final List<Driver> results;

    public Race(String name, Driver... drivers) {
        this.name = name;
        this.results = Arrays.asList(drivers);
    }

    public void calculateDiversPoint() {
        for (int i = 0; i < this.results.size(); i++) {
            Driver driver = this.results.get(i);
            driver.addPoints(POINTS[i]);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Driver> getResults() {
        return results;
    }
}
