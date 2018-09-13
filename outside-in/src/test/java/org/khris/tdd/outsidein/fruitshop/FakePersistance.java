package org.khris.tdd.outsidein.fruitshop;

import static org.khris.tdd.outsidein.fruitshop.Fruit.BANANE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.CERISE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.POMME;

import java.util.HashMap;
import java.util.Map;

public class FakePersistance implements Persistance {

	private static final Map<Fruit, Integer> PRIX_UNITAIRE_PAR_FRUIT = new HashMap<>();
	private static final int POURCENTAGE_REDUCTION_FIDELITE = 10;

	static {
		PRIX_UNITAIRE_PAR_FRUIT.put(POMME, 100);
		PRIX_UNITAIRE_PAR_FRUIT.put(BANANE, 150);
		PRIX_UNITAIRE_PAR_FRUIT.put(CERISE, 75);
	}

	public int getPrixUnitaireFruit(Fruit fruit) {
		return PRIX_UNITAIRE_PAR_FRUIT.get(fruit);
	}

	public int getPourcentageReductionFidelite() {
		return POURCENTAGE_REDUCTION_FIDELITE;
	}

}
