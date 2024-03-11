package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.infraestructure.exception.PriceParameterException;

import java.time.LocalDateTime;
import java.util.Objects;

public class RequestPriceCreateService {

    public RequestPrice createRequestPrice(LocalDateTime currentDate, Integer product, Integer brand) {
        if(Objects.isNull(currentDate)) {
            currentDate = LocalDateTime.now();
        }
        this.verifyData(product, brand);
        return new RequestPrice(currentDate, product, brand);
    }

    private  void verifyData(Integer product, Integer brand) {
        if(Objects.isNull(product) || Objects.isNull(brand)) {
            throw new PriceParameterException("Product or Brand cannot be null");
        }
        if(product < 0 || brand < 0) {
            throw new PriceParameterException("Product or Brand must be positive");
        }
    }
}
