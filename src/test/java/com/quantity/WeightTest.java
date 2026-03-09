package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeightTest {

    @Test
    void given1KgAnd1000Gram_whenCompared_shouldBeEqual() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertEquals(w1, w2);
    }

    @Test
    void givenKg_whenConvertedToPound_shouldReturnCorrectValue() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = w1.convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 1e-4);
    }

    @Test
    void givenTwoWeights_whenAdded_shouldReturnCorrectKg() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void givenTwoWeights_whenAddedInGram_shouldReturnCorrectValue() {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = w1.add(w2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 1e-6);
    }

    @Test
    void givenInvalidValue_whenCreatingWeight_shouldThrowException() {

        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    void givenNullUnit_whenCreatingWeight_shouldThrowException() {

        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(1.0, null));
    }
}