package com.quantity;

import java.util.Objects;

public class QuantityLength {

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
    
    public double getValue() {
        return value;
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException();

        double baseValue = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(result, targetUnit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException();

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        return new QuantityLength(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

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