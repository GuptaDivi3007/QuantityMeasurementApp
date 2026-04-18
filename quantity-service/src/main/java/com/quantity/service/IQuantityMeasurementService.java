package com.quantity.service;

import com.quantity.dto.QuantityInputDTO;
import com.quantity.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(QuantityInputDTO input);

    QuantityMeasurementEntity convert(QuantityInputDTO input);

    QuantityMeasurementEntity add(QuantityInputDTO input);

    List<QuantityMeasurementEntity> getHistoryByOperation(String operation);

    long getOperationCount(String operation);

    QuantityMeasurementEntity subtract(QuantityInputDTO input);

    QuantityMeasurementEntity multiply(QuantityInputDTO input);

    QuantityMeasurementEntity divide(QuantityInputDTO input);
}