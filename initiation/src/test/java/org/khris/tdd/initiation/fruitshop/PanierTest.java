package org.khris.tdd.initiation.fruitshop;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.khris.tdd.initiation.fruitshop.Fruit.BANANE;
import static org.khris.tdd.initiation.fruitshop.Fruit.CERISE;
import static org.khris.tdd.initiation.fruitshop.Fruit.POMME;

import java.util.List;

import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class PanierTest {

	private Panier panier = new Panier();

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
		int montantTotal = panier.getMontantTotal();

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
		int montantTotal = panier.getMontantTotal();

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
		int montantTotal = panier.getMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(montantAttendu);
	}

	@Test
	public void neRienEncaisserPourUnPanierVide() {

		// GIVEN
		// Le panier est vide

		// WHEN
		int montantTotal = panier.getMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(0);
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
		int montantTotal = panier.getMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(montantAttendu);
	}

	private void givenLePanierContient(List<Tuple> quantitesFruits) {
		for (Tuple quantiteFruit : quantitesFruits) {
			panier.ajouterFruit((Fruit) quantiteFruit.toArray()[1], (Integer) quantiteFruit.toArray()[0]);
		}
	}

	@Test
	public void appliquerLaReductionPourUnClientFidele() {

		// GIVEN
		panier.ajouterFruit(POMME, 10);

		// WHEN
		int montantTotal = panier.getMontantTotalClientFidele();

		// THEN
		assertThat(montantTotal).isEqualTo(450);
	}

}
