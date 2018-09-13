package org.khris.tdd.outsidein.fruitshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.khris.tdd.outsidein.fruitshop.Fruit.BANANE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.CERISE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.POMME;
import static org.mockito.BDDMockito.given;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TarificationTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private Persistance persistance;

	@InjectMocks
	private Tarification tarification;

	@Test
	public void tarificationNormalePourUnePomme() {

		// GIVEN
		given(persistance.getPrixUnitaireFruit(POMME)).willReturn(10);

		// WHEN
		int montant = tarification.calculerMontant(POMME, 1);

		// THEN
		assertThat(montant).isEqualTo(10);
	}

	@Test
	@Parameters({
			"1,  10",
			"2,  10",
			"3,  20",
			"10, 50",
			"25, 130"
	})
	public void unePommeSurDeuxEstGratuite(int quantite, int montantAttendu) {

		// GIVEN
		given(persistance.getPrixUnitaireFruit(POMME)).willReturn(10);

		// WHEN
		int montant = tarification.calculerMontant(POMME, quantite);

		// THEN
		assertThat(montant).isEqualTo(montantAttendu);
	}

	@Test
	public void tarificationNormalePourUneBanane() {

		// GIVEN
		given(persistance.getPrixUnitaireFruit(BANANE)).willReturn(20);

		// WHEN
		int montant = tarification.calculerMontant(BANANE, 1);

		// THEN
		assertThat(montant).isEqualTo(20);
	}

	@Test
	@Parameters({
			"1,  20",
			"2,  30",
			"3,  50",
			"10, 150",
			"25, 380"
	})
	public void uneBananeSurDeuxEstAMoitiePrix(int quantite, int montantAttendu) {

		// GIVEN
		given(persistance.getPrixUnitaireFruit(BANANE)).willReturn(20);

		// WHEN
		int montant = tarification.calculerMontant(BANANE, quantite);

		// THEN
		assertThat(montant).isEqualTo(montantAttendu);
	}

	@Test
	public void tarificationNormalePourUneCerise() {

		// GIVEN
		given(persistance.getPrixUnitaireFruit(CERISE)).willReturn(30);

		// WHEN
		int montant = tarification.calculerMontant(CERISE, 1);

		// THEN
		assertThat(montant).isEqualTo(30);
	}

	@Test
	public void tarificationNormalePourDeuxCerises() {

		// GIVEN
		given(persistance.getPrixUnitaireFruit(CERISE)).willReturn(30);

		// WHEN
		int montant = tarification.calculerMontant(CERISE, 2);

		// THEN
		assertThat(montant).isEqualTo(60);
	}

	@Test
	public void reductionAppliqueePourUnClientFidele() {

		// GIVEN
		given(persistance.getPourcentageReductionFidelite()).willReturn(20);

		// WHEN
		int montant = tarification.appliquerReductionFidelite(100);

		// THEN
		assertThat(montant).isEqualTo(80);
	}

}
