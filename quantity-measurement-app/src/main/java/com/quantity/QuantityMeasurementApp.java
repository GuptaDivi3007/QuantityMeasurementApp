package com.quantity;

public class QuantityMeasurementApp {

	public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double toInches() {
            return value * 12;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static class Inches {
        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double toInches() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static boolean compareFeet(double value1, double value2) {
        Feet f1 = new Feet(value1);
        Feet f2 = new Feet(value2);
        return f1.equals(f2);
    }

    public static boolean compareInches(double value1, double value2) {
        Inches i1 = new Inches(value1);
        Inches i2 = new Inches(value2);
        return i1.equals(i2);
    }

    public static boolean compareFeetAndInches(double feet, double inches) {
        Feet f = new Feet(feet);
        Inches i = new Inches(inches);
        return Double.compare(f.toInches(), i.toInches()) == 0;
    }

    public static void main(String[] args) {

        System.out.println("1.0 ft and 1.0 ft Equal? " + compareFeet(1.0, 1.0));
        System.out.println("1.0 inch and 1.0 inch Equal? " + compareInches(1.0, 1.0));
        System.out.println("1.0 ft and 12.0 inch Equal? " + compareFeetAndInches(1.0, 12.0));
    }
}
