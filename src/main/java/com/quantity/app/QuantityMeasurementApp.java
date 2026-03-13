package com.quantity.app;

import com.quantity.controller.*;
import com.quantity.dto.*;
import com.quantity.repository.*;
import com.quantity.service.*;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1,"FEET");
        QuantityDTO q2 = new QuantityDTO(12,"INCHES");

        controller.compare(q1,q2);

        controller.add(q1,q2);
    }
}