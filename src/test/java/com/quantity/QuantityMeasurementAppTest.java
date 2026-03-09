package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(3.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(6.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(12.0, result.convertTo(QuantityMeasurementApp.LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(2.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(24.0, result.convertTo(QuantityMeasurementApp.LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(2.0, result.convertTo(QuantityMeasurementApp.LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testAddition_WithZero() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(5.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(3.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }

    @Test
    void testAddition_LargeValues() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1e6, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(2e6, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }

    @Test
    void testAddition_SmallValues() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(0.001, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(0.002, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result = q1.add(q2);

        assertEquals(0.003, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }
}