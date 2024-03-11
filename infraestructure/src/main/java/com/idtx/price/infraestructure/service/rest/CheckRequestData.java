package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.infraestructure.exception.PriceParameterException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CheckRequestData {

    public void verifyData(RequestPrice requestPrice) {
        if(Objects.isNull(requestPrice.getProduct()) || Objects.isNull(requestPrice.getBrand())) {
            throw new PriceParameterException("Product or Brand cannot be null");
        }

        if(requestPrice.getProduct() < 0 || requestPrice.getBrand() < 0) {
            throw new PriceParameterException("Product or Brand must be positive");
        }

    }
}
