package org.khris.tdd.initiation.fruitshop;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.khris.tdd.initiation.fruitshop.Fruit.*;

@RunWith(JUnitParamsRunner.class)
public class CartTest {

    private Cart cart = new Cart();

    @Test
    @Parameters({
            " 1, 100",
            " 3, 200",
            "10, 500"
    })
    public void cashInExpectedAmountForApplesQuantity(int quantity, int expectedAmount) {

        // GIVEN
        cart.addFruit(APPLE, quantity);

        // WHEN
        int totalAmount = cart.getTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    @Test
    @Parameters({
            " 1,  150",
            " 4,  450",
            "12, 1350"
    })
    public void cashInExpectedAmountForBananasQuantity(int quantity, int expectedAmount) {

        // GIVEN
        cart.addFruit(BANANA, quantity);

        // WHEN
        int totalAmount = cart.getTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    @Test
    @Parameters({
            " 1,   75",
            " 6,  450",
            "14, 1050"
    })
    public void cashInExpectedAmountForCherriesQuantity(int quantity, int expectedAmount) {

        // GIVEN
        cart.addFruit(CHERRY, quantity);

        // WHEN
        int totalAmount = cart.getTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    @Test
    public void noCashInForEmptyBasket() {

        // GIVEN
        // Empty cart

        // WHEN
        int totalAmount = cart.getTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(0);
    }

    @SuppressWarnings("unused")
    private Object[][] parametersForCashInExpectedAmountForFruitQuantities() {
        return new Object[][]{

                // @formatter:off
                fruitQuantitiesAndExpectedAmount(emptyList(), 0),
                fruitQuantitiesAndExpectedAmount(singletonList(tuple(1, APPLE)), 100),
                fruitQuantitiesAndExpectedAmount(singletonList(tuple(1, BANANA)), 150),
                fruitQuantitiesAndExpectedAmount(singletonList(tuple(1, CHERRY)), 75),
                fruitQuantitiesAndExpectedAmount(asList(tuple(1, APPLE), tuple(1, BANANA), tuple(1, CHERRY)), 325),
                fruitQuantitiesAndExpectedAmount(asList(tuple(3, APPLE), tuple(6, BANANA), tuple(4, CHERRY)), 1175),
                fruitQuantitiesAndExpectedAmount(asList(tuple(12, APPLE), tuple(8, BANANA), tuple(10, CHERRY)), 2250),
                fruitQuantitiesAndExpectedAmount(asList(tuple(4, APPLE), tuple(2, BANANA), tuple(8, CHERRY)), 1025)
                // @formatter:on
        };
    }

    private Object[] fruitQuantitiesAndExpectedAmount(List fruitQuantities, int expectedAmount) {
        return new Object[]{fruitQuantities, expectedAmount};
    }

    @Test
    @Parameters
    public void cashInExpectedAmountForFruitQuantities(List<Tuple> fruitQuantities, int expectedAmount) {

        // GIVEN
        givenTheBasketContains(fruitQuantities);

        // WHEN
        int totalAmount = cart.getTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    private void givenTheBasketContains(List<Tuple> fruitQuantities) {
        for (Tuple fruitQuantity : fruitQuantities) {
            cart.addFruit((Fruit) fruitQuantity.toArray()[1], (Integer) fruitQuantity.toArray()[0]);
        }
    }

    @Test
    public void applyLoyaltyProgramDiscount() {

        // GIVEN
        cart.addFruit(APPLE, 10);

        // WHEN
        int totalAmount = cart.getTotalAmountIncludingLoyaltyProgram();

        // THEN
        assertThat(totalAmount).isEqualTo(450);
    }

}
