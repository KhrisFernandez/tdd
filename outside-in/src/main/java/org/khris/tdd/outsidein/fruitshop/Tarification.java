package org.khris.tdd.outsidein.fruitshop;

public class Tarification {

	private Persistance persistance;

	public Tarification(Persistance persistance) {
		this.persistance = persistance;
	}

	public int calculerMontant(Fruit fruit, int quantite) {

		int prixUnitaire = persistance.getPrixUnitaireFruit(fruit);

		switch (fruit) {
		case POMME:
			return ((quantite / 2) + (quantite % 2)) * prixUnitaire;
		case BANANE:
			return (((quantite / 2) + (quantite % 2)) * prixUnitaire) + (((quantite / 2)) * (prixUnitaire / 2));
		default:
			return quantite * prixUnitaire;
		}
	}

	public int appliquerReductionFidelite(int montant) {
		int pourcentageReduction = persistance.getPourcentageReductionFidelite();
		return montant * (100 - pourcentageReduction) / 100;
	}

}
