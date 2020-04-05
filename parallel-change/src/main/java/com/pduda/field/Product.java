package com.pduda.field;

public class Product {
    private Double price;
    private Integer number;

    public Product(double price, int number) {
        this.price = price;
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public boolean hasDiscount() {
        return price >= 100;
    }

    public Integer getNumber() {
        return number;
    }
}
