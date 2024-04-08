package com.idtx.price.application.service;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.application.dto.ResponsePrice;

import com.idtx.price.application.exception.PriceNotFoundException;
import com.idtx.price.domain.entity.dto.PriceDomain;

import com.idtx.price.domain.entity.service.PriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
public class PriceFinderService {

    private final PriceService priceService;

    private final PriceResponseMapper priceResponseMapper;

    public PriceFinderService(final PriceService priceService, final PriceResponseMapper priceResponseMapper) {
        this.priceService = priceService;
        this.priceResponseMapper = priceResponseMapper;
    }
    @Transactional
    public ResponsePrice getPriceByDateTime(RequestPrice request) {
        Optional<PriceDomain> price = priceService.getPriceByDateTime(request.getCurrentDate(), request.getProduct(), request.getBrand());
        return priceResponseMapper.convertToDto(price.orElseThrow(() -> new PriceNotFoundException("precio no encontrado")), request.getCurrentDate());

    }


}
