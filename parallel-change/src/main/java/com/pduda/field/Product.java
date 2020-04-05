package com.pduda.field;

public class Product {
    private Double price;

    public Product(double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public boolean hasDiscount() {
        return price >= 100;
    }
}
