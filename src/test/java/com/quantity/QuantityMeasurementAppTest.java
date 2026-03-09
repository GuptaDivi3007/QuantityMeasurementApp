package com.quantity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.convertTo(QuantityMeasurementApp.LengthUnit.FEET), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(24.0, result.convertTo(QuantityMeasurementApp.LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(0.666666, result.convertTo(QuantityMeasurementApp.LengthUnit.YARDS), 0.001);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.convertTo(QuantityMeasurementApp.LengthUnit.CENTIMETERS), 0.01);
    }

    @Test
    void testAddition_Commutativity() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result1 =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.QuantityLength result2 =
                QuantityMeasurementApp.QuantityLength.add(q2, q1, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(result1.convertTo(QuantityMeasurementApp.LengthUnit.YARDS),
                result2.convertTo(QuantityMeasurementApp.LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testAddition_WithZero() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCHES);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(1.6666, result.convertTo(QuantityMeasurementApp.LengthUnit.YARDS), 0.01);
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(q1, q2, QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(36.0, result.convertTo(QuantityMeasurementApp.LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testAddition_NullTargetUnit() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.QuantityLength.add(q1, q2, null));
    }
}