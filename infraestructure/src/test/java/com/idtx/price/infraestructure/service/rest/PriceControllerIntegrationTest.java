package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.service.PriceFinderService;
import com.idtx.price.domain.entity.service.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceControllerIntegrationTest {

    @Mock
    private PriceRepository priceRepository;
    private PriceFinderService priceFinderService;
    private PriceController priceController;

//    private PriceController getPriceController() {
//        priceAppService = new PriceAppService(priceRepository);
//        return new PriceController(priceAppService);
//    }

    @Test
    public void test1() {
      //  priceController = getPriceController();
        //priceController.getPrice()

    }
}
