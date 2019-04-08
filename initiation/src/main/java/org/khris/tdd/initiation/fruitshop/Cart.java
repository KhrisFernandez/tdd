package org.khris.tdd.initiation.fruitshop;

import java.util.HashMap;
import java.util.Map;

import static org.khris.tdd.initiation.fruitshop.Fruit.*;

public class Cart {

    private static final int PRICE_PER_UNIT_CHERRY = 75;
    private static final int PRICE_PER_UNIT_BANANA = 150;
    private static final int PRICE_PER_UNIT_APPLE = 100;
    private static final int LOYALTY_PROGRAM_DISCOUNT_PERCENT = 10;

    private Map<Fruit, Integer> fruits = new HashMap<>();

    public Cart() {
        fruits.put(APPLE, 0);
        fruits.put(BANANA, 0);
        fruits.put(CHERRY, 0);
    }

    public int getTotalAmount() {

        int applesAmount = computeApplesAmount(fruits.get(APPLE));

        int bananasAmount = computeBananasAmount(fruits.get(BANANA));

        int cherriesAmount = computeCherriesAmount(fruits.get(CHERRY));

        return applesAmount + bananasAmount + cherriesAmount;
    }

    public void addFruit(Fruit fruit, int quantity) {

        if (fruits.get(fruit) == null) {
            fruits.put(fruit, quantity);
        } else {
            Integer currentQuantity = fruits.get(fruit);
            fruits.put(fruit, currentQuantity + quantity);
        }
    }

    private int computeApplesAmount(int quantity) {
        return ((quantity / 2) + (quantity % 2)) * PRICE_PER_UNIT_APPLE;
    }

    private int computeBananasAmount(int quantity) {
        return (((quantity / 2) + (quantity % 2)) * PRICE_PER_UNIT_BANANA) + (((quantity / 2)) * (PRICE_PER_UNIT_BANANA / 2));
    }

    private int computeCherriesAmount(int quantity) {
        return quantity * PRICE_PER_UNIT_CHERRY;
    }

    public int getTotalAmountIncludingLoyaltyProgram() {
        return getTotalAmount() * (100 - LOYALTY_PROGRAM_DISCOUNT_PERCENT) / 100;
    }

}
