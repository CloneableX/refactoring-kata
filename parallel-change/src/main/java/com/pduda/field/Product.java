package com.pduda.field;

public class Product {
    private Double price;
    private Integer number;

    public Product(double price, int number) {
        this.price = price;
        this.number = number;
    }

    public boolean hasDiscount() {
        return price >= 100;
    }

    public Integer getNumber() {
        return number;
    }

    public Double calculatePrice() {
        return number * price;
    }
}
