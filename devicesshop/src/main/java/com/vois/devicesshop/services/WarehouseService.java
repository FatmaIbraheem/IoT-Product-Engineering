package com.vois.devicesshop.services;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.model.DeviceStatus;
import com.vois.devicesshop.model.SIMCard;
import com.vois.devicesshop.model.SIMCardStatus;
import com.vois.devicesshop.repository.IDevicesRepository;
import com.vois.devicesshop.repository.ISIMCardRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * This is for warehouse service operation
 *
 * @author Fatma Ibrahim
 * @since 17-11-2022
 *
 */

@Service
public class WarehouseService {
    @Autowired
    IDevicesRepository devicesRepository;

    @Autowired
    ISIMCardRepository simCardRepository;

    MongoTemplate mongoTemplate;

    /**
     * getting all devices record by using the method findaAll()
     *
     * @param
     * @return List<Device>
     * @throws
     */
    public List<Device> getAllDevices()
    {
        return devicesRepository.findAll();
    }

    /**
     * getting all SIM cards record by using the method findaAll()
     *
     * @param
     * @return List<SIMCard>
     * @throws
     */
    public List<SIMCard> getAllSIMCards()
    {
        return simCardRepository.findAll();
    }


    /**
     * getting all devices record by using the method findDevicesWithStatus()
     *
     * @param
     * @return List<Device>
     * @throws
     */
    public List<Device> getWaitingForActivationDevices()
    {
        List<Device> waitingForActivationDevicesLst = new ArrayList<Device>();
        List<Device> devicesLst = devicesRepository.findDevicesWithStatus(DeviceStatus.READY.getValue());
        for(Device device : devicesLst){
            if(device.getSimCard() != null && device.getSimCard().getSimStatus() != null && device.getSimCard().getSimStatus().equals(SIMCardStatus.WAITING_FOR_ACTIVATION)){
                waitingForActivationDevicesLst.add(device);
            }
        }
        return waitingForActivationDevicesLst;
    }

    /**
     * saving all devices record by using the method save()
     *
     * @param
     * @return List<Device>
     * @throws
     */
    public void saveDevice(List<Device> devicesLst){ devicesRepository.saveAll(devicesLst); }

    /**
     * saving all SIM cards record by using the method save()
     *
     * @param
     * @return List<SIMCard>
     * @throws
     */
    public void saveSIMCard(List<SIMCard> simCardsLst)
    {
        simCardRepository.saveAll(simCardsLst);
    }

    /**
     * save data to mongoDB
     *
     * @param
     * @return List<Device>
     * @throws
     */
    @PostConstruct
    public void initiateDBData(){
        List<SIMCard> simCardsLst = new ArrayList<SIMCard>();
        List<Device> devicesLst = new ArrayList<Device>();

        SIMCard simCard = new SIMCard(null, "SIM_1", "Egypt", SIMCardStatus.BLOCKED);
        simCardsLst.add(simCard);

        simCard = new SIMCard(null, "SIM_2", "Egypt", SIMCardStatus.ACTIVE);
        simCardsLst.add(simCard);

        simCard = new SIMCard(null, "SIM_3", "Egypt", SIMCardStatus.WAITING_FOR_ACTIVATION);
        simCardsLst.add(simCard);

        simCard = new SIMCard(null, "SIM_4", "Egypt", SIMCardStatus.DEACTIVATED);
        simCardsLst.add(simCard);

        Device device = new Device(null, "device_1", DeviceStatus.NOT_READY, 10, null);
        devicesLst.add(device);

        device = new Device(null, "device_2", DeviceStatus.NOT_READY, -29, null);
        devicesLst.add(device);

        device = new Device(null, "device_3", DeviceStatus.NOT_READY, 90, null);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_5", "Egypt", SIMCardStatus.DEACTIVATED);
        simCardsLst.add(simCard);
        device = new Device(null, "device_4", DeviceStatus.NOT_READY, 90, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_6", "Egypt", SIMCardStatus.WAITING_FOR_ACTIVATION);
        simCardsLst.add(simCard);
        device = new Device(null,"device_5", DeviceStatus.NOT_READY, 90, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_7", "Egypt", SIMCardStatus.ACTIVE);
        simCardsLst.add(simCard);
        device = new Device(null, "device_6", DeviceStatus.NOT_READY, 90, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_8", "Egypt", SIMCardStatus.BLOCKED);
        simCardsLst.add(simCard);
        device = new Device(null, "device_7", DeviceStatus.NOT_READY, 90, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null,"SIM_9", "Egypt", SIMCardStatus.DEACTIVATED);
        simCardsLst.add(simCard);
        device = new Device(null, "device_8", DeviceStatus.READY, 40, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_10", "Egypt", SIMCardStatus.WAITING_FOR_ACTIVATION);
        simCardsLst.add(simCard);
        device = new Device(null, "device_9", DeviceStatus.READY, 30, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_11", "Egypt", SIMCardStatus.ACTIVE);
        simCardsLst.add(simCard);
        device = new Device(null, "device_10", DeviceStatus.READY, 20, simCard);
        devicesLst.add(device);

        simCard = new SIMCard(null, "SIM_12", "Egypt", SIMCardStatus.BLOCKED);
        simCardsLst.add(simCard);
        device = new Device(null, "device_11", DeviceStatus.READY, 10, simCard);
        devicesLst.add(device);

        simCardRepository.saveAll(simCardsLst);
        devicesRepository.saveAll(devicesLst);
    }
}
