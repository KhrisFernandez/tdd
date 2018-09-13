package org.khris.tdd.outsidein.fruitshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.khris.tdd.outsidein.fruitshop.Fruit.BANANE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.CERISE;
import static org.khris.tdd.outsidein.fruitshop.Fruit.POMME;
import static org.mockito.BDDMockito.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class EncaissementTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private Panier panier;

	@Mock
	private Tarification tarification;

	@InjectMocks
	private Encaissement encaissement;

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
	public void encaisserLeMontantAttenduPourUneQuantiteDeFruits() {

		// GIVEN
		Map<Fruit, Integer> fruits = new HashMap<>();
		given(panier.listerFruits()).willReturn(fruits);

		fruits.put(POMME, 4);
		given(tarification.calculerMontant(POMME, 4)).willReturn(40);

		fruits.put(BANANE, 5);
		given(tarification.calculerMontant(BANANE, 5)).willReturn(100);

		fruits.put(CERISE, 6);
		given(tarification.calculerMontant(CERISE, 6)).willReturn(180);

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(320);
	}

	@Test
	public void appliquerLaReductionPourUnClientFidele() {

		// GIVEN
		Map<Fruit, Integer> fruits = new HashMap<>();
		given(panier.listerFruits()).willReturn(fruits);

		fruits.put(POMME, 10);
		given(tarification.calculerMontant(POMME, 10)).willReturn(100);

		encaissement.activerProgrammeFidelite();
		given(tarification.appliquerReductionFidelite(100)).willReturn(80);

		// WHEN
		int montantTotal = encaissement.calculerMontantTotal();

		// THEN
		assertThat(montantTotal).isEqualTo(80);
	}

}
