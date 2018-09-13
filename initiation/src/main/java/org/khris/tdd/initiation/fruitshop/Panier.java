package org.khris.tdd.initiation.fruitshop;

import static org.khris.tdd.initiation.fruitshop.Fruit.BANANE;
import static org.khris.tdd.initiation.fruitshop.Fruit.CERISE;
import static org.khris.tdd.initiation.fruitshop.Fruit.POMME;

import java.util.HashMap;
import java.util.Map;

public class Panier {

	private static final int PRIX_UNITAIRE_CERISE = 75;
	private static final int PRIX_UNITAIRE_BANANE = 150;
	private static final int PRIX_UNITAIRE_POMME = 100;
	private Map<Fruit, Integer> fruits = new HashMap<>();

	public Panier() {
		fruits.put(POMME, 0);
		fruits.put(BANANE, 0);
		fruits.put(CERISE, 0);
	}

	public int getMontantTotal() {

		int montantPommes = calculerMontantPomme(fruits.get(POMME));

		int montantBananes = calculerMontantBanane(fruits.get(BANANE));

		int montantCerises = calculerMontantCerise(fruits.get(CERISE));

		return montantPommes + montantBananes + montantCerises;
	}

	public void ajouterFruit(Fruit fruit, int quantite) {

		if (fruits.get(fruit) == null) {
			fruits.put(fruit, quantite);
		} else {
			Integer quantiteActuelle = fruits.get(fruit);
			fruits.put(fruit, quantiteActuelle + quantite);
		}
	}

	private int calculerMontantPomme(int quantite) {
		return ((quantite / 2) + (quantite % 2)) * PRIX_UNITAIRE_POMME;
	}

	private int calculerMontantBanane(int quantite) {
		return (((quantite / 2) + (quantite % 2)) * PRIX_UNITAIRE_BANANE) + (((quantite / 2)) * (PRIX_UNITAIRE_BANANE / 2));
	}

	private int calculerMontantCerise(int quantite) {
		return quantite * PRIX_UNITAIRE_CERISE;
	}

	public int getMontantTotalClientFidele() {
		return getMontantTotal() * (100 - 10) / 100;
	}

}
