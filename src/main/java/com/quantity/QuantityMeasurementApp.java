package com.quantity;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // -------- Length Example --------
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Addition:");
        System.out.println(l1.add(l2));


        // -------- Weight Example --------
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println("Weight Addition:");
        System.out.println(w1.add(w2));


        // -------- Volume Example --------
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("Volume Addition:");
        System.out.println(v1.add(v2));


        // -------- Temperature Equality --------
        Quantity<TemperatureUnit> t1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> t2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("Temperature Equality:");
        System.out.println(t1.equals(t2));


        // -------- Temperature Conversion --------
        System.out.println("Temperature Conversion:");
        System.out.println(t1.convertTo(TemperatureUnit.FAHRENHEIT));


        // -------- Unsupported Arithmetic Operation --------
        System.out.println("Temperature Arithmetic Test:");

        try {
            System.out.println(
                    t1.add(new Quantity<>(10.0, TemperatureUnit.CELSIUS))
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}