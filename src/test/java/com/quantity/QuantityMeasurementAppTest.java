package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0/12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(0.0328084, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {

        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q.convertTo(LengthUnit.INCHES);

        assertTrue(result.equals(new QuantityLength(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testQuantityLengthRefactored_Add() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2, LengthUnit.FEET);

        assertTrue(result.equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.6666,
                result.convertTo(LengthUnit.YARDS).getValue(),
                0.001);
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}