package org.khris.tdd.outsidein.fruitshop;

public interface Persistence {

    int getFruitPricePerUnit(Fruit fruit);

    int getLoyaltyProgramDiscountPercent();

}
