package com.quantity;

import com.quantity.controller.QuantityMeasurementController;
import com.quantity.LengthUnit;
import com.quantity.Quantity;
import com.quantity.dto.QuantityDTO;
import com.quantity.entity.QuantityMeasurementEntity;
import com.quantity.repository.QuantityMeasurementCacheRepository;
import com.quantity.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class QuantityMeasurementLayerTest {

    // ---------------- DTO TEST ----------------

    @Test
    public void givenDTO_WhenCreated_ShouldReturnCorrectValues() {

        QuantityDTO dto = new QuantityDTO(5, "FEET");

        Assertions.assertEquals(5, dto.getValue());
        Assertions.assertEquals("FEET", dto.getUnit());
    }

    // ---------------- ENTITY TEST ----------------

    @Test
    public void givenEntity_WhenCreated_ShouldStoreOperationAndResult() {

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", "2 FEET");

        Assertions.assertEquals("ADD", entity.getOperation());
        Assertions.assertEquals("2 FEET", entity.getResult());
    }

    // ---------------- CORE QUANTITY TEST ----------------

    @Test
    public void givenFeetAndInches_WhenCompared_ShouldReturnTrue() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12, LengthUnit.INCHES);

        Assertions.assertTrue(feet.equals(inches));
    }

    // ---------------- REPOSITORY TEST ----------------

    @Test
    public void givenRepository_WhenSavingEntity_ShouldStoreData() {

        QuantityMeasurementCacheRepository repo =
                QuantityMeasurementCacheRepository.getInstance();

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("TEST", "SUCCESS");

        repo.save(entity);

        Assertions.assertTrue(repo.findAll().contains(entity));
    }

    // ---------------- SERVICE TEST ----------------

    @Test
    public void givenDTOObjects_WhenAddedThroughService_ShouldReturnCorrectResult() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        QuantityDTO result = service.add(q1, q2);

        Assertions.assertEquals(2, result.getValue(), 0.01);
        Assertions.assertEquals("FEET", result.getUnit());
    }

    @Test
    public void givenDTOObjects_WhenComparedThroughService_ShouldReturnTrue() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        boolean result = service.compare(q1, q2);

        Assertions.assertTrue(result);
    }

    // ---------------- CONTROLLER TEST ----------------

    @Test
    public void givenController_WhenCompareCalled_ShouldPrintTrue() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        controller.compare(q1, q2);

        String result = output.toString().trim();

        Assertions.assertTrue(result.contains("true"));
    }

    @Test
    public void givenController_WhenAddCalled_ShouldPrintResult() {

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(
                        QuantityMeasurementCacheRepository.getInstance()
                );

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 = new QuantityDTO(1, "FEET");
        QuantityDTO q2 = new QuantityDTO(12, "INCHES");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        controller.add(q1, q2);

        String result = output.toString().trim();

        Assertions.assertTrue(result.contains("2"));
    }
}