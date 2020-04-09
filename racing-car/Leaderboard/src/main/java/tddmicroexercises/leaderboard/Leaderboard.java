package tddmicroexercises.leaderboard;

import java.util.*;
import java.util.stream.Collectors;

public class Leaderboard {

    private final List<Race> races;

    public Leaderboard(Race... races) {
        this.races = Arrays.asList(races);
    }

    public Map<String, Integer> driverResults() {
        Map<String, Integer> results = new HashMap<>();
        calculateDriversPoint().forEach(driver -> results.put(driver.getName(), driver.getPoint()));
        return results;
    }

    private Set<Driver> calculateDriversPoint() {
        Set<Driver> drivers = new HashSet<>();
        for (Race race : this.races) {
            race.calculateDiversPoint();
            drivers.addAll(race.getResults());
        }
        return drivers;
    }

    public List<String> driverRankings() {
        Set<Driver> results = calculateDriversPoint();
        return results.stream()
                .sorted()
                .map(Driver::getName)
                .collect(Collectors.toList());
    }

}
