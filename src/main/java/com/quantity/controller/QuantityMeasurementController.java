package com.quantity.controller;

import com.quantity.dto.QuantityDTO;
import com.quantity.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    // Compare Quantities
    public void compare(QuantityDTO q1, QuantityDTO q2) {

        boolean result = service.compare(q1, q2);

        System.out.println("Equality Result : " + result);
    }
    
    // Convert Quantities
    public void convert(QuantityDTO q1, String target) {

        QuantityDTO result = service.convert(q1, target);

        System.out.println("Converted Result : " + result.getValue() + " " + result.getUnit());
    }

    // Add Quantities
    public void add(QuantityDTO q1, QuantityDTO q2) {

        QuantityDTO result = service.add(q1, q2);

        System.out.println("Addition Result : " +
                result.getValue() + " " + result.getUnit());
    }

    // Subtract Quantities
    public void subtract(QuantityDTO q1, QuantityDTO q2) {

        QuantityDTO result = service.subtract(q1, q2);

        System.out.println("Subtraction Result : " +
                result.getValue() + " " + result.getUnit());
    }

    // Divide Quantities
    public void divide(QuantityDTO q1, QuantityDTO q2) {

        double result = service.divide(q1, q2);

        System.out.println("Division Result : " + result);
    }
}