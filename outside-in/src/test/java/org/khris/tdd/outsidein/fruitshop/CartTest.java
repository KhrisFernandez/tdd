package org.khris.tdd.outsidein.fruitshop;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.khris.tdd.outsidein.fruitshop.Fruit.*;

@RunWith(JUnitParamsRunner.class)
public class CartTest {

    @Test
    public void cartIsEmpty() {

        // GIVEN
        Cart cart = new Cart();

        // WHEN
        Map<Fruit, Integer> fruits = cart.listFruits();

        // THEN
        assertThat(fruits).isEmpty();
    }

    @Test
    @Parameters({
            "0",
            "1",
            "3",
            "10"
    })
    public void cartContainsQuantityApples(int quantity) {

        // GIVEN
        Cart cart = new Cart();
        cart.addFruit(APPLE, quantity);

        // WHEN
        Map<Fruit, Integer> fruits = cart.listFruits();

        // THEN
        assertThat(fruits.get(APPLE)).isEqualTo(quantity);
    }

    @Test
    @Parameters({
            "0",
            "1",
            "4",
            "12"
    })
    public void cartContainsQuantityBananas(int quantity) {

        // GIVEN
        Cart cart = new Cart();
        cart.addFruit(BANANA, quantity);

        // WHEN
        Map<Fruit, Integer> fruits = cart.listFruits();

        // THEN
        assertThat(fruits.get(BANANA)).isEqualTo(quantity);
    }

    @Test
    @Parameters({
            " 0",
            " 1",
            " 6",
            "14"
    })
    public void cartContainsQuantityCherries(int quantity) {

        // GIVEN
        Cart cart = new Cart();
        cart.addFruit(CHERRY, quantity);

        // WHEN
        Map<Fruit, Integer> fruits = cart.listFruits();

        // THEN
        assertThat(fruits.get(CHERRY)).isEqualTo(quantity);
    }

    @Test
    public void cartContainsQuantitiesOfFruits() {

        // GIVEN
        Cart cart = new Cart();
        cart.addFruit(APPLE, 1);
        cart.addFruit(BANANA, 2);
        cart.addFruit(CHERRY, 3);

        // WHEN
        Map<Fruit, Integer> fruits = cart.listFruits();

        // THEN
        assertThat(fruits.get(APPLE)).isEqualTo(1);
        assertThat(fruits.get(BANANA)).isEqualTo(2);
        assertThat(fruits.get(CHERRY)).isEqualTo(3);
    }

}
