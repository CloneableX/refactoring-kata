package com.pduda.field;

public class Product {
    public static final int PRICE_DISCOUNT_BASE_LINE = 100;
    private Double price;
    private Integer number;

    public Product(double price, int number) {
        this.price = price;
        this.number = number;
    }

    public boolean hasDiscount() {
        return price >= PRICE_DISCOUNT_BASE_LINE;
    }

    public Integer getNumber() {
        return number;
    }

    public Double calculatePrice() {
        return number * price;
    }
}
