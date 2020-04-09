package tddmicroexercises.leaderboard;

import java.util.*;

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
        Map<String, Integer> results = driverResults();
        List<String> resultsList = new ArrayList<>(results.keySet());
        resultsList.sort(new DriverByPointsDescendingComparator(results));
        return resultsList;
    }

    private static final class DriverByPointsDescendingComparator implements Comparator<String> {
        private final Map<String, Integer> results;

        private DriverByPointsDescendingComparator(Map<String, Integer> results) {
            this.results = results;
        }

        @Override
        public int compare(String driverName1, String driverName2) {
            return -results.get(driverName1).compareTo(results.get(driverName2));
        }
    }

}
