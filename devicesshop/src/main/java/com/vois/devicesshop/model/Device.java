package com.vois.devicesshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("devices")
@Data
@AllArgsConstructor
public class Device {
    @Id
    private String deviceId;

    @Indexed(unique = true)
    private String name;

    private DeviceStatus deviceStatus;
    private int temperature;

    @DBRef
    private SIMCard simCard;
}
