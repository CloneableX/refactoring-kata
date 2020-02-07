package com.adaptionsoft.games;

public enum Category {
    Pop, Science, Sports, Rock, Blues, History;

    static Category getCategory(int place) {
        Category[] categories = Category.values();
        Category category = categories[place % categories.length];

        System.out.println("The category is " + category);
        return category;
    }
}
