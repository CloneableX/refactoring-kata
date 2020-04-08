package tddmicroexercises.leaderboard;

import java.util.*;

public class Leaderboard {

    private final List<Race> races;

    public Leaderboard(Race... races) {
        this.races = Arrays.asList(races);
    }

    public Map<String, Integer> driverResults() {
        Map<String, Integer> results = new HashMap<>();
        for (Race race : this.races) {
            calculateDiversPoint(results, race);
        }
        return results;
    }

    private void calculateDiversPoint(Map<String, Integer> results, Race race) {
        for (Driver driver : race.getResults()) {
            String driverName = race.getDriverName(driver);
            int points = race.getPoints(driver);
            saveDriverToResult(results, driverName, points);
        }
    }

    private void saveDriverToResult(Map<String, Integer> results, String driverName, int points) {
        if (results.containsKey(driverName)) {
            results.put(driverName, results.get(driverName) + points);
        } else {
            results.put(driverName, points);
        }
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
