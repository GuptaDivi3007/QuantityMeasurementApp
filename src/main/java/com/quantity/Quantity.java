package com.quantity;

public class Quantity<U extends Enum<U> & IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------- CONVERSION (UPDATED FOR UC15) ----------

    public Quantity convertTo(IMeasurable targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity(convertedValue, (Enum) targetUnit);
    }

    // ---------- ADDITION ----------

    public Quantity<U> add(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(base1 + base2);

        return new Quantity<>(result, unit);
    }

    // ---------- SUBTRACTION ----------

    public Quantity<U> subtract(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double result = unit.convertFromBaseUnit(base1 - base2);

        return new Quantity<>(result, unit);
    }

    // ---------- DIVISION ----------

    public double divide(Quantity<U> other) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return base1 / base2;
    }

    // ---------- EQUALITY ----------

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Quantity<?> q))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) q.unit).convertToBaseUnit(q.value);

        return Math.abs(base1 - base2) < 0.0001;
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}