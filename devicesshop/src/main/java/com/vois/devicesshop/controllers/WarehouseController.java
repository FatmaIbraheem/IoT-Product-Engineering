package com.vois.devicesshop.controllers;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.model.SIMCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vois.devicesshop.services.WarehouseService;

import java.util.List;

/**
 * This is for warehouse controller
 *
 * @author Fatma Ibrahim
 * @since 17-11-2022
 *
 */

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    //autowire the WarehouseService class
    @Autowired
    WarehouseService warehouseService;

    //creating post mapping that save the SIM Cards detail in the database
    @PostMapping("/save-simcards")
    private void saveSIMCards(@RequestBody List<SIMCard> sIMCardsLst) {
        warehouseService.saveSIMCard(sIMCardsLst);
    }

    //creating a get mapping that retrieves all SIM Cards details from the database
    @GetMapping("/get-all-simcards")
    private List<SIMCard> getAllSIMCards()
    {
        return warehouseService.getAllSIMCards();
    }

    //creating post mapping that save the device detail in the database
    @PostMapping("/save-devices")
    private void saveDevice(@RequestBody List<Device> devicesLst) {
        warehouseService.saveDevice(devicesLst);
    }

    //creating a get mapping that retrieves all devices details from the database
    @GetMapping("/get-all-devices")
    private List<Device> getAllDevices()
    {
        return warehouseService.getAllDevices();
    }

    //creating a get mapping that retrieves waiting for activation devices
    @GetMapping("/waiting-for-activation-devices")
    private List<Device> getWaitingForActivationDevices()
    {
        return warehouseService.getWaitingForActivationDevices();
    }
}
