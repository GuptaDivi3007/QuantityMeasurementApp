package com.quantity;

import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-6;

    public QuantityWeight(double value, WeightUnit unit) {

        if (!Double.isFinite(value))
            throw new IllegalArgumentException();

        if (unit == null)
            throw new IllegalArgumentException();

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException();

        double baseValue = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityWeight(result, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {

        if (other == null)
            throw new IllegalArgumentException();

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = unit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException();

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityWeight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityWeight other = (QuantityWeight) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}