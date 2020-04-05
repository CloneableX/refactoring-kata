package com.pduda.field;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
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

        assertThat(shoppingCart.calculateTotalPrice(), is(10d));
    }

    @Test
    public void should_has_discount_when_any_products_worth_at_least_100() throws Exception {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(99);
        shoppingCart.add(100);
        shoppingCart.add(10);

        Assert.assertTrue(shoppingCart.hasDiscount());
    }

    @Test
    public void should_not_has_discount_when_no_products_worth_at_least_100() throws Exception {
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
        assertThat(shoppingCart.calculateTotalPrice(), is(30d));
    }

    @Test
    public void should_has_not_discount_when_not_have_product() {
        ShoppingCart shoppingCart = new ShoppingCart();

        assertFalse(shoppingCart.hasDiscount());
    }

    @Test
    public void should_discount_price_when_calculate_total_price_at_least_one_worth_at_least_100() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(100);
        shoppingCart.add(10);

        assertThat(shoppingCart.calculateTotalPrice(), is((100 + 10) * ShoppingCart.DISCOUNT));
    }

    @Test
    public void should_count_products_number_when_add_multiple_number_products_item() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(10, 2);
        shoppingCart.add(30, 3);

        assertThat(shoppingCart.numberOfProducts(), is(5));
    }

    @Test
    public void should_count_all_number_products_price_when_add_multiple_number_products_item() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(10, 2);
        shoppingCart.add(30, 3);

        assertThat(shoppingCart.calculateTotalPrice(), is((double) (10 * 2 + 30 * 3)));
    }
}
