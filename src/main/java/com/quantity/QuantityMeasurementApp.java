package com.quantity;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2));

        System.out.println(w1.convertTo(WeightUnit.POUND));

        QuantityWeight result1 = w1.add(w2);
        System.out.println(result1);

        QuantityWeight result2 = w1.add(w2, WeightUnit.GRAM);
        System.out.println(result2);

        QuantityWeight w3 = new QuantityWeight(2.0, WeightUnit.POUND);
        System.out.println(w3.convertTo(WeightUnit.KILOGRAM));


        
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(l1.equals(l2));

        System.out.println(l1.convertTo(LengthUnit.YARDS));

        QuantityLength result3 = l1.add(l2, LengthUnit.FEET);
        System.out.println(result3);

        QuantityLength result4 = l1.add(l2, LengthUnit.INCHES);
        System.out.println(result4);

        QuantityLength l3 = new QuantityLength(3.0, LengthUnit.YARDS);
        System.out.println(l3.convertTo(LengthUnit.FEET));
    }
}