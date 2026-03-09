package com.quantity;

import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException();
            if (unit == null)
                throw new IllegalArgumentException();

            this.value = value;
            this.unit = unit;
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null)
                throw new IllegalArgumentException();

            double feet = unit.toFeet(value);
            return targetUnit.fromFeet(feet);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException();

            if (source == null || target == null)
                throw new IllegalArgumentException();

            double feet = source.toFeet(value);
            return target.fromFeet(feet);
        }

        public QuantityLength add(QuantityLength other) {

            if (other == null)
                throw new IllegalArgumentException();

            double feet1 = unit.toFeet(value);
            double feet2 = other.unit.toFeet(other.value);

            double sumFeet = feet1 + feet2;

            double result = unit.fromFeet(sumFeet);

            return new QuantityLength(result, unit);
        }

        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {

            if (q1 == null || q2 == null || targetUnit == null)
                throw new IllegalArgumentException();

            double feet1 = q1.unit.toFeet(q1.value);
            double feet2 = q2.unit.toFeet(q2.value);

            double sumFeet = feet1 + feet2;

            double result = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (!(obj instanceof QuantityLength))
                return false;

            QuantityLength other = (QuantityLength) obj;

            double thisFeet = unit.toFeet(value);
            double otherFeet = other.unit.toFeet(other.value);

            return Math.abs(thisFeet - otherFeet) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit.toFeet(value));
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2);

        System.out.println(result);
    }
}