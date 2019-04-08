package org.khris.tdd.outsidein.fruitshop;

public class Pricing {

    private Persistence persistence;

    public Pricing(Persistence persistence) {
        this.persistence = persistence;
    }

    public int computeAmount(Fruit fruit, int quantity) {

        int pricePerUnit = persistence.getFruitPricePerUnit(fruit);

        switch (fruit) {
            case APPLE:
                return ((quantity / 2) + (quantity % 2)) * pricePerUnit;
            case BANANA:
                return (((quantity / 2) + (quantity % 2)) * pricePerUnit) + (((quantity / 2)) * (pricePerUnit / 2));
            default:
                return quantity * pricePerUnit;
        }
    }

    public int applyLoyaltyProgramDiscount(int amount) {
        int discountPercent = persistence.getLoyaltyProgramDiscountPercent();
        return amount * (100 - discountPercent) / 100;
    }

}
