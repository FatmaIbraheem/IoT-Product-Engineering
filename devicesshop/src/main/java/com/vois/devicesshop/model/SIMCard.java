package com.vois.devicesshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("simCard")
@Data
@AllArgsConstructor
public class SIMCard {
    @Id
    private String simCardId;

    @Indexed(unique = true)
    private String operatorCode;

    private String country;
    private SIMCardStatus simStatus;
}
