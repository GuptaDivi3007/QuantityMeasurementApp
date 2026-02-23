package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(72.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(1.0, result, 1e-4);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(6.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.5;

        double toInch = QuantityMeasurementApp.QuantityLength
                .convert(original,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH);

        double backToFeet = QuantityMeasurementApp.QuantityLength
                .convert(toInch,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(original, backToFeet, EPSILON);
    }

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(-12.0, result, EPSILON);
    }

    @Test
    void testConversion_SameUnit() {
        double result = QuantityMeasurementApp.QuantityLength
                .convert(5.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(5.0, result, EPSILON);
    }

    @Test
    void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength
                        .convert(1.0,
                                null,
                                QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength
                        .convert(Double.NaN,
                                QuantityMeasurementApp.LengthUnit.FEET,
                                QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    void testConversion_Infinite_Throws() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength
                        .convert(Double.POSITIVE_INFINITY,
                                QuantityMeasurementApp.LengthUnit.FEET,
                                QuantityMeasurementApp.LengthUnit.INCH));
    }
}