package com.pduda.field;

public class Product {
    private int price;

    public Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean hasDiscount() {
        return price >= 100;
    }
}
