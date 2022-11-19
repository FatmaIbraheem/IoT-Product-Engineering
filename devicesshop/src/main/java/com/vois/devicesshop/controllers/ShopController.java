package com.vois.devicesshop.controllers;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is for shop controller
 *
 * @author Fatma Ibrahim
 * @since 17-11-2022
 *
 */

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    //creating delete mapping that deletes a specified device
    @DeleteMapping("/delete/{device-id}")
    private void deleteDevice(@PathVariable("device-id") String deviceId)
    {
        shopService.deleteDevice(deviceId);
    }

    //creating put mapping that updates the Device detail
    @PutMapping("/update")
    private void update(@RequestBody Device device)
    {
        shopService.update(device);
    }

    //creating a get mapping that retrieves waiting for activation devices
    @GetMapping("/devices-available-for-sale")
    private List<Device> getDevicesAvailableForSale()
    {
        return shopService.getDevicesAvailableForSale();
    }
}
