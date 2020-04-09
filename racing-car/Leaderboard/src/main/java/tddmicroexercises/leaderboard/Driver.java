package tddmicroexercises.leaderboard;

import java.util.Comparator;

public class Driver implements Comparator<Driver>, Comparable<Driver> {

    private final String name;
    private final String country;
    private Integer point = 0;

    public Driver(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + country.hashCode();
    }

    @Override
    public int compare(Driver driver, Driver otherDriver) {
        return -driver.getName().compareTo(otherDriver.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) obj;
        return this.name.equals(other.name) && this.country.equals(other.country);
    }

    public void addPoints(int points) {
        this.point += points;
    }

    public int getPoint() {
        return this.point;
    }

    @Override
    public int compareTo(Driver driver) {
        return -this.point.compareTo(driver.getPoint());
    }
}
