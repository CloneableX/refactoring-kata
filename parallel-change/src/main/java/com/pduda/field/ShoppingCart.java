package com.pduda.field;

public class ShoppingCart {
    private int price = 0;
    private int productsNumber = 0;

    public void add(int price) {
        this.price += price;
        productsNumber++;
    }

    public int calculateTotalPrice() {
        return price;
    }

    public boolean hasDiscount() {
        return price >= 100;
    }

    public int numberOfProducts() {
        return productsNumber;
    }
}
