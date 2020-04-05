package com.pduda.field;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public static final double DISCOUNT = 0.9d;
    private List<Product> products = new ArrayList<>();

    public void add(double price) {
        products.add(new Product(price));
    }

    public double calculateTotalPrice() {
        Double reducePrice = products.stream()
                .reduce(0d,
                        (totalPrice, product) -> totalPrice + product.getPrice(),
                        Double::sum);

        if (hasDiscount()) {
            return reducePrice * DISCOUNT;
        }
        return reducePrice;
    }

    public boolean hasDiscount() {
        if (products.size() == 0) {
            return false;
        }

        long discountNumber = products.stream()
                .filter(Product::hasDiscount)
                .count();
        return discountNumber > 0;
    }

    public int numberOfProducts() {
        return products.size();
    }
}
