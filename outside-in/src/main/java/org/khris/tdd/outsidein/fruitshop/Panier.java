package org.khris.tdd.outsidein.fruitshop;

import java.util.HashMap;
import java.util.Map;

public class Panier {

	private Map<Fruit, Integer> fruits = new HashMap<>();

	public void ajouterFruit(Fruit fruit, int quantite) {

		if (fruits.get(fruit) == null) {
			fruits.put(fruit, quantite);
		} else {
			Integer quantiteActuelle = fruits.get(fruit);
			fruits.put(fruit, quantiteActuelle + quantite);
		}
	}

	public Map<Fruit, Integer> listerFruits() {
		return fruits;
	}

}
