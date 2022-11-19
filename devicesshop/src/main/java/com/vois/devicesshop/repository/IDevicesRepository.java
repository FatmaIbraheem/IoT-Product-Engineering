package com.vois.devicesshop.repository;

import com.vois.devicesshop.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDevicesRepository extends MongoRepository<Device, String> {
    @Query("{deviceStatus:'?0'}")
    List<Device> findDevicesWithStatus(String deviceStatus);
}
