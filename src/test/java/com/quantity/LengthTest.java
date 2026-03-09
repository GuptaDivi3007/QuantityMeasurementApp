package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LengthTest {

    @Test
    void given1FeetAnd12Inches_whenCompared_shouldBeEqual() {

        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(l1, l2);
    }

    @Test
    void givenFeet_whenConvertedToInches_shouldReturnCorrectValue() {

        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = l1.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 1e-6);
    }

    @Test
    void givenTwoLengths_whenAdded_shouldReturnCorrectFeet() {

        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = l1.add(l2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void givenTwoLengths_whenAddedInInches_shouldReturnCorrectValue() {

        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = l1.add(l2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 1e-6);
    }

    @Test
    void givenInvalidValue_whenCreatingLength_shouldThrowException() {

        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void givenNullUnit_whenCreatingLength_shouldThrowException() {

        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(1.0, null));
    }
}