package org.khris.tdd.outsidein.fruitshop;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.khris.tdd.outsidein.fruitshop.Fruit.BANANE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.CERISE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.POMME;

import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class FruitShopTest {

	private Panier panier = new Panier();
	private Persistance persistance = new FakePersistance();
	private Tarification tarification = new Tarification(persistance);
	private Encaissement encaissement = new Encaissement(panier, tarification);

	@Test
	public void neRienEncaisserPourUnPanierVide() {

		// GIVEN
		// Le panier est vide

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(0);
	}

	@Test
	@Parameters({
			" 1, 100",
			" 3, 200",
			"10, 500"
	})
	public void encaisserLeMontantAttenduPourUneQuantiteDePommes(int quantite, int montantAttendu) {

		// GIVEN
		panier.ajouterFruit(POMME, quantite);

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(montantAttendu);
	}

	@Test
	@Parameters({
			" 1,  150",
			" 4,  450",
			"12, 1350"
	})
	public void encaisserLeMontantAttenduPourUneQuantiteDeBananes(int quantite, int montantAttendu) {

		// GIVEN
		panier.ajouterFruit(BANANE, quantite);

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(montantAttendu);
	}

	@Test
	@Parameters({
			" 1,   75",
			" 6,  450",
			"14, 1050"
	})
	public void encaisserLeMontantAttenduPourUneQuantiteDeCerises(int quantite, int montantAttendu) {

		// GIVEN
		panier.ajouterFruit(CERISE, quantite);

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(montantAttendu);
	}

	@SuppressWarnings("unused")
	private Object[][] parametersForEncaisserLeMontantAttenduPourUneQuantiteDeFruits() {
		return new Object[][] {

				// @formatter:off
				quantitesFruitsEtMontantAttendu(emptyList(),                                                   0 ),
				quantitesFruitsEtMontantAttendu(singletonList(tuple(1, POMME)),                                100 ),
				quantitesFruitsEtMontantAttendu(singletonList(tuple(1, BANANE)),                               150 ),
				quantitesFruitsEtMontantAttendu(singletonList(tuple(1, CERISE)),                               75 ),
				quantitesFruitsEtMontantAttendu(asList(tuple( 1, POMME), tuple(1, BANANE), tuple( 1, CERISE)), 325 ),
				quantitesFruitsEtMontantAttendu(asList(tuple( 3, POMME), tuple(6, BANANE), tuple( 4, CERISE)), 1175 ),
				quantitesFruitsEtMontantAttendu(asList(tuple(12, POMME), tuple(8, BANANE), tuple(10, CERISE)), 2250 ),
				quantitesFruitsEtMontantAttendu(asList(tuple( 4, POMME), tuple(2, BANANE), tuple( 8, CERISE)), 1025 )
				// @formatter:on
		};
	}

	private Object[] quantitesFruitsEtMontantAttendu(List quantitesFruits, int montantAttendu) {
		return new Object[] { quantitesFruits, montantAttendu };
	}

	@Test
	@Parameters
	public void encaisserLeMontantAttenduPourUneQuantiteDeFruits(List<Tuple> quantitesFruits, int montantAttendu) {

		// GIVEN
		givenLePanierContient(quantitesFruits);

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(montantAttendu);
	}

	private Integer getQuantite(Tuple quantiteFruit) {
		return (Integer) quantiteFruit.toArray()[0];
	}

	private Fruit getFruit(Tuple quantiteFruit) {
		return (Fruit) quantiteFruit.toArray()[1];
	}

	private void givenLePanierContient(List<Tuple> quantitesFruits) {
		for (Tuple quantiteFruit : quantitesFruits) {
			panier.ajouterFruit(getFruit(quantiteFruit), getQuantite(quantiteFruit));
		}
	}

	@Test
	public void appliquerLaReductionPourUnClientFidele() {

		// GIVEN
		panier.ajouterFruit(POMME, 10);
		encaissement.activerProgrammeFidelite();

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(450);
	}

}
