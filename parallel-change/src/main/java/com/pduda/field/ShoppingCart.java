package com.pduda.field;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public static final double DISCOUNT = 0.9d;
    private List<Product> products = new ArrayList<>();

    public void add(int price) {
        products.add(new Product(price));
    }

    public double calculateTotalPrice() {
        Integer reducePrice = products.stream()
                .reduce(0,
                        (totalPrice, product) -> totalPrice + product.getPrice(),
                        Integer::sum);

        if (hasDiscount()) {
            return reducePrice * DISCOUNT;
        }
        return Double.parseDouble(String.valueOf(reducePrice));
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
