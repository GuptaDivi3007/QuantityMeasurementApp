package com.quantity.service;

import com.quantity.*;
import com.quantity.dto.QuantityDTO;
import com.quantity.repository.*;
import com.quantity.entity.*;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository){
        this.repository = repository;
    }

    private Quantity<?> buildQuantity(QuantityDTO dto){

        switch(dto.getUnit().toUpperCase()){

            case "FEET":
                return new Quantity<>(dto.getValue(), LengthUnit.FEET);

            case "INCHES":
                return new Quantity<>(dto.getValue(), LengthUnit.INCHES);

            case "LITRE":
                return new Quantity<>(dto.getValue(), VolumeUnit.LITRE);

            case "MILLILITRE":
                return new Quantity<>(dto.getValue(), VolumeUnit.MILLILITRE);

            case "KILOGRAM":
                return new Quantity<>(dto.getValue(), WeightUnit.KILOGRAM);

            case "GRAM":
                return new Quantity<>(dto.getValue(), WeightUnit.GRAM);

            default:
                throw new RuntimeException("Unsupported unit");
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> a = buildQuantity(q1);
        Quantity<?> b = buildQuantity(q2);

        Quantity<?> result = a.add((Quantity) b);

        repository.save(new QuantityMeasurementEntity("ADD", result.toString(), q1.getValue() + " " + q1.getUnit(), q2.getValue() + " " + q2.getUnit()));

        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> a = buildQuantity(q1);
        Quantity<?> b = buildQuantity(q2);

        Quantity<?> result = a.subtract((Quantity) b);

        repository.save(new QuantityMeasurementEntity("SUBTRACT", result.toString(), q1.getValue() + " " + q1.getUnit(), q2.getValue() + " " + q2.getUnit()));

        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> a = buildQuantity(q1);
        Quantity<?> b = buildQuantity(q2);

        double result = a.divide((Quantity) b);

        repository.save(new QuantityMeasurementEntity("DIVIDE", String.valueOf(result), q1.getValue() + " " + q1.getUnit(), q2.getValue() + " " + q2.getUnit()));

        return result;
    }

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity<?> a = buildQuantity(q1);
        Quantity<?> b = buildQuantity(q2);

        boolean result = a.equals(b);

        repository.save(new QuantityMeasurementEntity("COMPARE", String.valueOf(result), q1.getValue() + " " + q1.getUnit(), q2.getValue() + " " + q2.getUnit()));

        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO input, String targetUnit) {

        Quantity<?> q = buildQuantity(input);

        LengthUnit target = LengthUnit.valueOf(targetUnit.toUpperCase());

        Quantity<?> result = q.convertTo(target);

        repository.save(new QuantityMeasurementEntity("CONVERT", result.toString(), input.getValue() + " " + input.getUnit(), targetUnit));

        return new QuantityDTO(result.getValue(), result.getUnit().toString());
    }
}