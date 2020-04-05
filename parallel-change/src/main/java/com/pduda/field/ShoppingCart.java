package com.pduda.field;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public static final double DISCOUNT = 0.9d;
    private List<Product> products = new ArrayList<>();

    public void add(double price) {
        add(price, 1);
    }

    public void add(double price, int number) {
        products.add(new Product(price, number));
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
        return products.stream()
                .reduce(0, (totalNumber, product) -> totalNumber + product.getNumber(), Integer::sum);
    }

}
