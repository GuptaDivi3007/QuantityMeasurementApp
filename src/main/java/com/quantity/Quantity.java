package com.quantity;

import java.util.Objects;

public class Quantity<U extends Enum<U> & IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------------- Conversion ----------------

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Incompatible unit types");

        double baseValue = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    // ---------------- Addition ----------------

    public Quantity<U> add(Quantity<U> other) {

        validateOperand(other);
        unit.validateOperationSupport("ADD");

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateOperand(other);
        unit.validateOperationSupport("ADD");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundToTwoDecimals(result), targetUnit);
    }

    // ---------------- Subtraction ----------------

    public Quantity<U> subtract(Quantity<U> other) {

        validateOperand(other);
        unit.validateOperationSupport("SUBTRACT");

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = unit.convertFromBaseUnit(resultBase);

        return new Quantity<>(roundToTwoDecimals(result), unit);
    }

    // ---------------- Division ----------------

    public double divide(Quantity<U> other) {

        validateOperand(other);
        unit.validateOperationSupport("DIVIDE");

        double base1 = unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return roundToTwoDecimals(base1 / base2);
    }

    // ---------------- Helper Methods ----------------

    private void validateOperand(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different measurement categories");
    }

    private double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    // ---------------- Equality ----------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?> q))
            return false;

        if (!unit.getClass().equals(q.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) q.unit).convertToBaseUnit(q.value);

        return Math.abs(base1 - base2) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}