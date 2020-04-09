package tddmicroexercises.leaderboard;

import java.util.*;
import java.util.stream.Collectors;

public class LeaderBoard {

    private final List<Race> races;

    public LeaderBoard(Race... races) {
        this.races = Arrays.asList(races);
    }

    public Set<Driver> driverResults() {
        Set<Driver> drivers = new HashSet<>();
        for (Race race : this.races) {
            race.calculateDiversPoint();
            drivers.addAll(race.getResults());
        }
        return drivers;
    }

    public List<String> driverRankings() {
        Set<Driver> results = driverResults();
        return results.stream()
                .sorted()
                .map(Driver::getName)
                .collect(Collectors.toList());
    }

}
