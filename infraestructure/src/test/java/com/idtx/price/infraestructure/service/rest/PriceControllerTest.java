package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.exception.PriceNotFoundException;
import com.idtx.price.application.service.PriceFinderService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceFinderService priceFinderService;
    @Mock
    private CheckRequestData checkRequestData;

    private PriceController priceController = new PriceController(priceFinderService, checkRequestData);


    @Test()
    public void salida_no_nula() {
        var result = priceController.getPrice(null, null, null);
        assertThat(result).isNotNull();

    }

    @Test()
    public void salida_con_responseEntity_status() {
        var result = priceController.getPrice(null, null, null);
        assertThat(result).isNotNull().isInstanceOf(ResponseEntity.class);
    }

    @Test()
    public void salida_con_entradas_nulas()  {
        PriceNotFoundException thrown =  assertThrows(
                PriceNotFoundException.class,
                () -> priceController.getPrice(null, null, null)
                );

    }

}