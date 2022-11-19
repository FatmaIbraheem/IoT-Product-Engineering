package com.vois.devicesshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeviceStatus {
    READY("READY"),
    NOT_READY("NOT_READY");

    private String value;
}
