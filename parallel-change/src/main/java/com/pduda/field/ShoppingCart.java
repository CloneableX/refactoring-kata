package com.pduda.field;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int price;
    private List<Product> products = new ArrayList<>();

    public void add(int price) {
        this.price = price;
        products.add(new Product(price));
    }

    public int calculateTotalPrice() {
        return products.stream()
                .reduce(0,
                        (totalPrice, product) -> totalPrice + product.getPrice(),
                        Integer::sum);
    }

    public boolean hasDiscount() {
        return price >= 100;
    }

    public int numberOfProducts() {
        return products.size();
    }
}
