package org.khris.tdd.outsidein.fruitshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.khris.tdd.outsidein.fruitshop.Fruit.BANANE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.CERISE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.POMME;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PanierTest {

	@Test
	public void lePanierEstVide() {

		// GIVEN
		Panier panier = new Panier();

		// WHEN
		Map<Fruit, Integer> fruits = panier.listerFruits();

		// THEN
		assertThat(fruits.isEmpty());
	}

	@Test
	@Parameters({
			"0",
			"1",
			"3",
			"10"
	})
	public void lePanierContientQuantitePommes(int quantite) {

		// GIVEN
		Panier panier = new Panier();
		panier.ajouterFruit(POMME, quantite);

		// WHEN
		Map<Fruit, Integer> fruits = panier.listerFruits();

		// THEN
		assertThat(fruits.get(POMME)).isEqualTo(quantite);
	}

	@Test
	@Parameters({
			"0",
			"1",
			"4",
			"12"
	})
	public void lePanierContientQuantiteBananes(int quantite) {

		// GIVEN
		Panier panier = new Panier();
		panier.ajouterFruit(BANANE, quantite);

		// WHEN
		Map<Fruit, Integer> fruits = panier.listerFruits();

		// THEN
		assertThat(fruits.get(BANANE)).isEqualTo(quantite);
	}

	@Test
	@Parameters({
			" 0",
			" 1",
			" 6",
			"14"
	})
	public void lePanierContientQuantiteCerises(int quantite) {

		// GIVEN
		Panier panier = new Panier();
		panier.ajouterFruit(CERISE, quantite);

		// WHEN
		Map<Fruit, Integer> fruits = panier.listerFruits();

		// THEN
		assertThat(fruits.get(CERISE)).isEqualTo(quantite);
	}

	@Test
	public void lePanierContientLaBonneQuantiteDeDiversFruits() {

		// GIVEN
		Panier panier = new Panier();
		panier.ajouterFruit(POMME, 1);
		panier.ajouterFruit(BANANE, 2);
		panier.ajouterFruit(CERISE, 3);

		// WHEN
		Map<Fruit, Integer> fruits = panier.listerFruits();

		// THEN
		assertThat(fruits.get(POMME)).isEqualTo(1);
		assertThat(fruits.get(BANANE)).isEqualTo(2);
		assertThat(fruits.get(CERISE)).isEqualTo(3);
	}

}
