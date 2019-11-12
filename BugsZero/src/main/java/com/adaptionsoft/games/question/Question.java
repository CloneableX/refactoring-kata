package com.adaptionsoft.games.question;

public class Question {
    public static final String pop = "Pop";
    public static final String science = "Science";
    public static final String sports = "Sports";
    public static final String rock = "Rock";
    private String name;
    private int index;

    protected Question(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getCurrentCategory(int place) {
        if (isPop(place)) {
            return pop;
        }

        if (isScience(place)) {
            return science;
        }

        if (isSports(place)) {
            return sports;
        }
        return rock;
    }

    private static boolean isSports(int place) {
        return place == 2 || place == 6 || place == 10;
    }

    private static boolean isScience(int place) {
        return place == 1 || place == 5 || place == 9;
    }

    private static boolean isPop(int place) {
        return place == 0 || place == 4 || place == 8;
    }

    @Override
    public String toString() {
        return name + " Question " + index;
    }
}
