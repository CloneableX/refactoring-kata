package com.adaptionsoft.games;

public enum Category {
    Pop, Science, Sports, Rock;

    static Category getCategory(int place) {
        if (place == 0) return Pop;
        if (place == 4) return Pop;
        if (place == 8) return Pop;
        if (place == 1) return Science;
        if (place == 5) return Science;
        if (place == 9) return Science;
        if (place == 2) return Sports;
        if (place == 6) return Sports;
        if (place == 10) return Sports;
        return Rock;
    }
}
