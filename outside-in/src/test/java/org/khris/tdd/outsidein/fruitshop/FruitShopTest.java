package org.khris.tdd.outsidein.fruitshop;

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
import static org.khris.tdd.outsidein.fruitshop.Fruit.*;

@RunWith(JUnitParamsRunner.class)
public class FruitShopTest {

    private Cart cart = new Cart();
    private Persistence persistence = new FakePersistence();
    private Pricing pricing = new Pricing(persistence);
    private Checkout checkout = new Checkout(cart, pricing);

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
    @Parameters({
            " 1, 100",
            " 3, 200",
            "10, 500"
    })
    public void checkoutExpectedAmountForApplesQuantity(int quantity, int expectedAmount) {

        // GIVEN
        cart.addFruit(APPLE, quantity);

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    @Test
    @Parameters({
            " 1,  150",
            " 4,  450",
            "12, 1350"
    })
    public void checkoutExpectedAmountForBananasQuantity(int quantity, int expectedAmount) {

        // GIVEN
        cart.addFruit(BANANA, quantity);

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    @Test
    @Parameters({
            " 1,   75",
            " 6,  450",
            "14, 1050"
    })
    public void checkoutExpectedAmountForCherriesQuantity(int quantity, int expectedAmount) {

        // GIVEN
        cart.addFruit(CHERRY, quantity);

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    @SuppressWarnings("unused")
    private Object[][] parametersForCheckoutExpectedAmountForFruitQuantities() {
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
    public void checkoutExpectedAmountForFruitQuantities(List<Tuple> fruitQuantities, int expectedAmount) {

        // GIVEN
        givenCartContains(fruitQuantities);

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(expectedAmount);
    }

    private Integer getQuantity(Tuple fruitQuantity) {
        return (Integer) fruitQuantity.toArray()[0];
    }

    private Fruit getFruit(Tuple fruitQuantity) {
        return (Fruit) fruitQuantity.toArray()[1];
    }

    private void givenCartContains(List<Tuple> fruitQuantities) {
        for (Tuple fruitQuantity : fruitQuantities) {
            cart.addFruit(getFruit(fruitQuantity), getQuantity(fruitQuantity));
        }
    }

    @Test
    public void applyLoyaltyProgramDiscount() {

        // GIVEN
        cart.addFruit(APPLE, 10);
        checkout.enableLoyaltyProgram();

        // WHEN
        int totalAmount = checkout.computeTotalAmount();

        // THEN
        assertThat(totalAmount).isEqualTo(450);
    }

}
