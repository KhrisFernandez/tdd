package org.khris.tdd.outsidein.fruitshop;

import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.khris.tdd.outsidein.fruitshop.Fruit.*;
import static org.mockito.BDDMockito.given;

@RunWith(JUnitParamsRunner.class)
public class CheckoutTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Cart cart;

    @Mock
    private Pricing pricing;

    @InjectMocks
    private Checkout checkout;

    @Test
    public void noCheckoutForEmptyCart() {

        // GIVEN
        // Empty cart

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(0);
    }

    @Test
    public void checkoutExpectedAmountForFruitQuantities() {

        // GIVEN
        Map<Fruit, Integer> fruits = new HashMap<>();
        given(cart.listFruits()).willReturn(fruits);

        fruits.put(APPLE, 4);
        given(pricing.computeAmount(APPLE, 4)).willReturn(40);

        fruits.put(BANANA, 5);
        given(pricing.computeAmount(BANANA, 5)).willReturn(100);

        fruits.put(CHERRY, 6);
        given(pricing.computeAmount(CHERRY, 6)).willReturn(180);

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(320);
    }

    @Test
    public void applyLoyaltyProgramDiscount() {

        // GIVEN
        Map<Fruit, Integer> fruits = new HashMap<>();
        given(cart.listFruits()).willReturn(fruits);

        fruits.put(APPLE, 10);
        given(pricing.computeAmount(APPLE, 10)).willReturn(100);

        checkout.enableLoyaltyProgram();
        given(pricing.applyLoyaltyProgramDiscount(100)).willReturn(80);

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(80);
    }

}
