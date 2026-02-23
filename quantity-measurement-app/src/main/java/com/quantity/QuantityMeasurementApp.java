package com.quantity;

import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
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
                throw new IllegalArgumentException("Invalid numeric value");
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double feetValue = unit.toFeet(value);
            return targetUnit.fromFeet(feetValue);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");
            if (source == null || target == null)
                throw new IllegalArgumentException("Unit cannot be null");

            double feetValue = source.toFeet(value);
            return target.fromFeet(feetValue);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (!(obj instanceof QuantityLength))
                return false;

            QuantityLength other = (QuantityLength) obj;

            double thisFeet = unit.toFeet(this.value);
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

    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return QuantityLength.convert(value, from, to);
    }

    public static double demonstrateLengthConversion(QuantityLength length, LengthUnit to) {
        return length.convertTo(to);
    }

    public static boolean demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        return q1.equals(q2);
    }

    public static void main(String[] args) {

        System.out.println(demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println(demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println(demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARDS));
        System.out.println(demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCH));

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(demonstrateLengthEquality(q1, q2));
    }
}