package com.vois.devicesshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SIMCardStatus {
    ACTIVE("ACTIVE"),
    WAITING_FOR_ACTIVATION("WAITING_FOR_ACTIVATION"),
    BLOCKED("BLOCKED"),
    DEACTIVATED("DEACTIVATED");

    private String value;
}
