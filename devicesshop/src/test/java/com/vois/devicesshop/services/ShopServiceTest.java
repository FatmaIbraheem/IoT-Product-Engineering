package com.vois.devicesshop.services;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.model.DeviceStatus;
import com.vois.devicesshop.model.SIMCard;
import com.vois.devicesshop.model.SIMCardStatus;
import com.vois.devicesshop.repository.IDevicesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShopServiceTest {
    @InjectMocks
    private ShopService shopServiceUnderTest = new ShopService();

    @Mock
    private IDevicesRepository devicesRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDeleteDevice() {
        // Setup
        // Run the test
        shopServiceUnderTest.deleteDevice("deviceId");

        // Verify the results
        verify(shopServiceUnderTest.devicesRepository).deleteById("deviceId");
    }

    @Test
    void testUpdate() {
        // Setup
        final Device updatedDevice = new Device("deviceId", "name", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE));

        // Configure IDevicesRepository.findById(...).
        final Optional<Device> device = Optional.of(new Device("deviceId", "name", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)));
        Mockito.when(devicesRepository.findById("deviceId")).thenReturn(device);

        // Configure IDevicesRepository.save(...).
        final Device device1 = new Device("deviceId", "name", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE));
        Mockito.when(devicesRepository.save(new Device("deviceId", "name", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)))).thenReturn(device1);

        // Run the test
        shopServiceUnderTest.update(updatedDevice);

        // Verify the results
        verify(devicesRepository).save(new Device("deviceId", "name", DeviceStatus.READY, 0,
                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)));
    }

//    @Test
//    void testUpdate_IDevicesRepositoryFindByIdReturnsAbsent() {
//        // Setup
//        final Device updatedDevice = new Device("deviceId", "name", DeviceStatus.READY, 0,
//                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE));
//        Mockito.when(devicesRepository.findById("deviceId")).thenReturn(Optional.empty());
//
//        // Configure IDevicesRepository.save(...).
//        final Device device = new Device("deviceId", "name", DeviceStatus.READY, 0,
//                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE));
//        Mockito.when(devicesRepository.save(new Device("deviceId", "name", DeviceStatus.READY, 0,
//                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)))).thenReturn(device);
//
//        // Run the test
//        shopServiceUnderTest.update(updatedDevice);
//
//        // Verify the results
//        verify(devicesRepository).save(new Device("deviceId", "name", DeviceStatus.READY, 0,
//                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)));
//    }

//    @Test
//    void testGetDevicesAvailableForSale() {
//        // Setup
//        final List<Device> expectedResult = Arrays.asList(new Device("deviceId", "name", DeviceStatus.READY, 0,
//                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)));
//
//        // Configure IDevicesRepository.findDevicesWithStatus(...).
//        final List<Device> devices = Arrays.asList(new Device("deviceId", "name", DeviceStatus.READY, 0,
//                new SIMCard("simCardId", "operatorCode", "country", SIMCardStatus.ACTIVE)));
//        Mockito.when(devicesRepository.findDevicesWithStatus(SIMCardStatus.ACTIVE.getValue())).thenReturn(devices);
//
//        // Run the test
//        final List<Device> result = shopServiceUnderTest.getDevicesAvailableForSale();
//
//        // Verify the results
//        assertEquals(expectedResult, result);
//    }

    @Test
    void testGetDevicesAvailableForSale_IDevicesRepositoryReturnsNoItems() {
        // Setup
        Mockito.when(devicesRepository.findDevicesWithStatus(Mockito.any())).thenReturn(Collections.emptyList());

        // Run the test
        final List<Device> result = shopServiceUnderTest.getDevicesAvailableForSale();

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }
}
