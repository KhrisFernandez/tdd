package org.khris.tdd.outsidein.fruitshop;

import java.util.Map;

public class Checkout {

    private Cart cart;
    private Pricing pricing;
    private boolean loyaltyProgramEnabled = false;

    public Checkout(Cart cart, Pricing pricing) {
        this.cart = cart;
        this.pricing = pricing;
    }

    public int computeTotalAmount() {

        int totalAmount = 0;

        Map<Fruit, Integer> fruits = cart.listFruits();
        for (Fruit fruit : fruits.keySet()) {
            int quantity = fruits.get(fruit);
            totalAmount += pricing.computeAmount(fruit, quantity);
        }

        if (loyaltyProgramEnabled) {
            totalAmount = pricing.applyLoyaltyProgramDiscount(totalAmount);
        }

        return totalAmount;
    }

    public void enableLoyaltyProgram() {
        this.loyaltyProgramEnabled = true;
    }

}
