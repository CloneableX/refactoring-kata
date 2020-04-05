package com.pduda.field;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingCartTest {


    @Test
    public void singleItem_numberOfProductsInTheCart() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(10);

        assertEquals(1, shoppingCart.numberOfProducts());
    }

    @Test
    public void singleItem_totalPrice() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(10);

        assertEquals(10, shoppingCart.calculateTotalPrice());
    }

    @Test
    public void singleItem_hasDiscountIfContainsAtLeastOneProductWorthAtLeast100() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(100);

        Assert.assertTrue(shoppingCart.hasDiscount());
    }

    @Test
    public void singleItem_doesNotHaveDiscountIfContainsNoProductsWorthAtLeast100() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(99);
        shoppingCart.add(10);

        Assert.assertFalse(shoppingCart.hasDiscount());
    }

    @Test
    public void should_get_2_products_when_add_2_items() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(10);
        shoppingCart.add(20);

        assertEquals(2, shoppingCart.numberOfProducts());
        assertEquals(30, shoppingCart.calculateTotalPrice());
    }

    @Test
    public void should_has_not_discount_when_not_have_product() {
        ShoppingCart shoppingCart = new ShoppingCart();

        assertFalse(shoppingCart.hasDiscount());
    }
}
