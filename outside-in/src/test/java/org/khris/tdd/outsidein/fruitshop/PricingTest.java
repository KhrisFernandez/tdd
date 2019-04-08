package org.khris.tdd.outsidein.fruitshop;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.khris.tdd.outsidein.fruitshop.Fruit.*;
import static org.mockito.BDDMockito.given;

@RunWith(JUnitParamsRunner.class)
public class PricingTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Persistence persistence;

    @InjectMocks
    private Pricing pricing;

    @Test
    public void normalPricingForOneApple() {

        // GIVEN
        given(persistence.getFruitPricePerUnit(APPLE)).willReturn(10);

        // WHEN
        int amount = pricing.computeAmount(APPLE, 1);

        // THEN
        assertThat(amount).isEqualTo(10);
    }

    @Test
    @Parameters({
            "1,  10",
            "2,  10",
            "3,  20",
            "10, 50",
            "25, 130"
    })
    public void oneAppleOutOfTwoIsFree(int quantity, int expectedAmount) {

        // GIVEN
        given(persistence.getFruitPricePerUnit(APPLE)).willReturn(10);

        // WHEN
        int amount = pricing.computeAmount(APPLE, quantity);

        // THEN
        assertThat(amount).isEqualTo(expectedAmount);
    }

    @Test
    public void normalPricingForOneBanana() {

        // GIVEN
        given(persistence.getFruitPricePerUnit(BANANA)).willReturn(20);

        // WHEN
        int amount = pricing.computeAmount(BANANA, 1);

        // THEN
        assertThat(amount).isEqualTo(20);
    }

    @Test
    @Parameters({
            "1,  20",
            "2,  30",
            "3,  50",
            "10, 150",
            "25, 380"
    })
    public void oneBananaOutOfTwoIsHalfPriced(int quantity, int expectedAmount) {

        // GIVEN
        given(persistence.getFruitPricePerUnit(BANANA)).willReturn(20);

        // WHEN
        int amount = pricing.computeAmount(BANANA, quantity);

        // THEN
        assertThat(amount).isEqualTo(expectedAmount);
    }

    @Test
    public void normalPricingForOneCherry() {

        // GIVEN
        given(persistence.getFruitPricePerUnit(CHERRY)).willReturn(30);

        // WHEN
        int amount = pricing.computeAmount(CHERRY, 1);

        // THEN
        assertThat(amount).isEqualTo(30);
    }

    @Test
    public void normalPricingForTwoCherries() {

        // GIVEN
        given(persistence.getFruitPricePerUnit(CHERRY)).willReturn(30);

        // WHEN
        int amount = pricing.computeAmount(CHERRY, 2);

        // THEN
        assertThat(amount).isEqualTo(60);
    }

    @Test
    public void applyLoyaltyProgramDiscount() {

        // GIVEN
        given(persistence.getLoyaltyProgramDiscountPercent()).willReturn(20);

        // WHEN
        int amount = pricing.applyLoyaltyProgramDiscount(100);

        // THEN
        assertThat(amount).isEqualTo(80);
    }

}
