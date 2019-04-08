package org.khris.tdd.outsidein.fruitshop;

import java.util.HashMap;
import java.util.Map;

import static org.khris.tdd.outsidein.fruitshop.Fruit.*;

public class FakePersistence implements Persistence {

    private static final Map<Fruit, Integer> FRUIT_PRICE_PER_UNIT = new HashMap<>();
    private static final int LOYALTY_PROGRAM_DISCOUNT_PERCENT = 10;

    static {
        FRUIT_PRICE_PER_UNIT.put(APPLE, 100);
        FRUIT_PRICE_PER_UNIT.put(BANANA, 150);
        FRUIT_PRICE_PER_UNIT.put(CHERRY, 75);
    }

    public int getFruitPricePerUnit(Fruit fruit) {
        return FRUIT_PRICE_PER_UNIT.get(fruit);
    }

    public int getLoyaltyProgramDiscountPercent() {
        return LOYALTY_PROGRAM_DISCOUNT_PERCENT;
    }

}
