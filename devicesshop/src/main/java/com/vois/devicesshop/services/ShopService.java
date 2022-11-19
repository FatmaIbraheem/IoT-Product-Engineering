package com.vois.devicesshop.services;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.model.DeviceStatus;
import com.vois.devicesshop.repository.IDevicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is for shop service operation
 *
 * @author Fatma Ibrahim
 * @since 17-11-2022
 *
 */

@Service
public class ShopService {
    @Autowired
    IDevicesRepository devicesRepository;

    @Value("${temperature.mini}")
    private int miniTemperature;

    @Value("${temperature.max}")
    private int maxTemperature;

    /**
     * getting all devices record by using the method deleteById()
     *
     * @param
     * @return Boolean
     * @throws
     */
    public void deleteDevice(String deviceId) //6378073b1f9e760976bee0a0
    {
        devicesRepository.deleteById(deviceId);
    }

    /**
     * getting all devices record by using the method findaAll()
     *
     * @param
     * @return List<Device>
     * @throws
     */
    public void update(Device updatedDevice)
    {
        Optional<Device> deviceOptional = devicesRepository.findById(updatedDevice.getDeviceId());
        if (deviceOptional.isPresent()){
            Device device = deviceOptional.get();

            device.setName(updatedDevice.getName() != null ? updatedDevice.getName() : device.getName());
            device.setTemperature(updatedDevice.getTemperature());
            device.setSimCard(updatedDevice.getSimCard() != null ? updatedDevice.getSimCard() : device.getSimCard());

            if((miniTemperature <= device.getTemperature() && device.getTemperature()<= maxTemperature) && device.getSimCard() != null){
                device.setDeviceStatus(DeviceStatus.READY);
            } else{
                device.setDeviceStatus(DeviceStatus.NOT_READY);
            }

            devicesRepository.save(device);
        }
    }

    /**
     * getting all devices record by using the method findDevicesAvailableForSale()
     *
     * @param
     * @return List<Device>
     * @throws
     */
    public List<Device> getDevicesAvailableForSale()
    {
        return devicesRepository.findDevicesWithStatus(DeviceStatus.READY.getValue());
    }
}
