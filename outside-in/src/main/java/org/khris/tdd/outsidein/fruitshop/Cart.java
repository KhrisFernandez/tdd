package org.khris.tdd.outsidein.fruitshop;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Fruit, Integer> fruits = new HashMap<>();

    public void addFruit(Fruit fruit, int quantity) {

        if (fruits.get(fruit) == null) {
            fruits.put(fruit, quantity);
        } else {
            Integer currentQuantity = fruits.get(fruit);
            fruits.put(fruit, currentQuantity + quantity);
        }
    }

    public Map<Fruit, Integer> listFruits() {
        return fruits;
    }

}
