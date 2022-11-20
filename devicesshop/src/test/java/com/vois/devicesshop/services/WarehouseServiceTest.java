package com.vois.devicesshop.services;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.model.DeviceStatus;
import com.vois.devicesshop.model.SIMCard;
import com.vois.devicesshop.model.SIMCardStatus;
import com.vois.devicesshop.repository.IDevicesRepository;
import com.vois.devicesshop.repository.ISIMCardRepository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class WarehouseServiceTest {

    @InjectMocks
    private WarehouseService warehouseServiceUnderTest = new WarehouseService();

    @Mock
    private IDevicesRepository devicesRepository;

    @Mock
    private ISIMCardRepository simCardRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllDevices() {
        // Setup
        final List<Device> expectedResult = Arrays.asList(new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)));

        // Configure IDevicesRepository.findAll(...).
        final List<Device> devices = Arrays.asList(new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)));
        when(devicesRepository.findAll()).thenReturn(devices);

        // Run the test
        final List<Device> result = warehouseServiceUnderTest.getAllDevices();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllDevices_IDevicesRepositoryReturnsNoItems() {
        // Setup
        when(devicesRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Device> result = warehouseServiceUnderTest.getAllDevices();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetAllSIMCards() {
        // Setup
        final List<SIMCard> expectedResult = Arrays.asList(
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE));

        // Configure ISIMCardRepository.findAll(...).
        final List<SIMCard> simCards = Arrays.asList(new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE));
        when(simCardRepository.findAll()).thenReturn(simCards);

        // Run the test
        final List<SIMCard> result = warehouseServiceUnderTest.getAllSIMCards();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllSIMCards_ISIMCardRepositoryReturnsNoItems() {
        // Setup
        when(simCardRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<SIMCard> result = warehouseServiceUnderTest.getAllSIMCards();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetWaitingForActivationDevices_IDevicesRepositoryReturnsNoItems() {
        // Setup
        when(devicesRepository.findDevicesWithStatus("value"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Device> result = warehouseServiceUnderTest.getWaitingForActivationDevices();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSaveDevice() {
        // Setup
        final List<Device> devicesLst = Arrays.asList(new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)));

        // Configure IDevicesRepository.saveAll(...).
        final List<Device> devices = Arrays.asList(new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)));
        when(devicesRepository.saveAll(Arrays.asList(
                new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                        new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE))))).thenReturn(devices);

        // Run the test
        warehouseServiceUnderTest.saveDevice(devicesLst);

        // Verify the results
        verify(devicesRepository).saveAll(Arrays.asList(
                new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                        new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE))));
    }

    @Test
    void testSaveDevice_IDevicesRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final List<Device> devicesLst = Arrays.asList(new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)));
        when(devicesRepository.saveAll(Arrays.asList(
                new Device("deviceId", "device_1", DeviceStatus.READY, 0,
                        new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)))))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThrows(OptimisticLockingFailureException.class, () -> warehouseServiceUnderTest.saveDevice(devicesLst));
    }

    @Test
    void testSaveSIMCard() {
        // Setup
        final List<SIMCard> simCardsLst = Arrays.asList(
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE));

        // Configure ISIMCardRepository.saveAll(...).
        final List<SIMCard> simCards = Arrays.asList(new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE));
        when(simCardRepository.saveAll(
                Arrays.asList(new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)))).thenReturn(simCards);

        // Run the test
        warehouseServiceUnderTest.saveSIMCard(simCardsLst);

        // Verify the results
        verify(simCardRepository).saveAll(
                Arrays.asList(new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE)));
    }

    @Test
    void testSaveSIMCard_ISIMCardRepositoryThrowsOptimisticLockingFailureException() {
        // Setup
        final List<SIMCard> simCardsLst = Arrays.asList(
                new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE));
        when(simCardRepository.saveAll(
                Arrays.asList(new SIMCard("simCardId", "SIM_1", "Egypt", SIMCardStatus.ACTIVE))))
                .thenThrow(OptimisticLockingFailureException.class);

        // Run the test
        assertThrows(OptimisticLockingFailureException.class, () -> warehouseServiceUnderTest.saveSIMCard(simCardsLst));
    }
}
