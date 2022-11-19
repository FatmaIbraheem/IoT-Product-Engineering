package com.vois.devicesshop.controllers;

import com.vois.devicesshop.model.Device;
import com.vois.devicesshop.services.ShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopControllerTest {

    private ShopController shopControllerUnderTest;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        shopControllerUnderTest = new ShopController();
        shopControllerUnderTest.shopService = mock(ShopService.class);
    }

    @Test
    public void getDevicesAvailableForSale() throws Exception {
        mvc.perform(get("/api/shop/devices-available-for-sale")).andExpect(status().isOk());
    }
}
