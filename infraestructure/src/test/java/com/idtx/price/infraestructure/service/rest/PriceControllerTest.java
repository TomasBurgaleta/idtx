package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.service.PriceFinderService;

import com.idtx.price.infraestructure.exception.PriceParameterException;
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

    private PriceController priceController;


    @Test()
    public void salida_con_responseEntity_status() {
        priceController = new PriceController(priceFinderService);
        var result = priceController.getPrice(null, 555, 1);
        assertThat(result).isNotNull().isInstanceOf(ResponseEntity.class);
    }

    @Test()
    public void salida_con_entradas_nulas()  {
        priceController = new PriceController(priceFinderService);
        PriceParameterException thrown =  assertThrows(
                PriceParameterException.class,
                () -> priceController.getPrice(null, null, null)
                );

    }

}