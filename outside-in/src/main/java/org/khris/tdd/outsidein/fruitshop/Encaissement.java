package org.khris.tdd.outsidein.fruitshop;

import java.util.Map;

public class Encaissement {

	private Panier panier;
	private Tarification tarification;
	private boolean programmeFideliteActive = false;

	public Encaissement(Panier panier, Tarification tarification) {
		this.panier = panier;
		this.tarification = tarification;
	}

	public int calculerMontantTotal() {

		int montantTotal = 0;

		Map<Fruit, Integer> fruits = panier.listerFruits();
		for (Fruit fruit : fruits.keySet()) {
			int quantite = fruits.get(fruit);
			montantTotal += tarification.calculerMontant(fruit, quantite);
		}

		if (programmeFideliteActive) {
			montantTotal = tarification.appliquerReductionFidelite(montantTotal);
		}

		return montantTotal;
	}

	public void activerProgrammeFidelite() {
		this.programmeFideliteActive = true;
	}

}
