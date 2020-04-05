package com.pduda.field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void add(int price) {
        products.add(new Product(price));
    }

    public int calculateTotalPrice() {
        return products.stream()
                .reduce(0,
                        (totalPrice, product) -> totalPrice + product.getPrice(),
                        Integer::sum);
    }

    public boolean hasDiscount() {
        if (products.size() == 0) {
            return false;
        }

        return calculateTotalPrice() >= 100;
    }

    public int numberOfProducts() {
        return products.size();
    }
}
