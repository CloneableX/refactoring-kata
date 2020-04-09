package tddmicroexercises.leaderboard;

public class Driver {

    private final String name;
    private final String country;
    private int point = 0;

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
}
