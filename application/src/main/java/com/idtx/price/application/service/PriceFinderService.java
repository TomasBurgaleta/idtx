package com.idtx.price.application.service;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.application.dto.ResponsePrice;

import com.idtx.price.domain.entity.entity.Price;
import com.idtx.price.domain.entity.service.PriceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceFinderService {

    private final PriceService priceService;

    private final ModelMapper modelMapper;

    public PriceFinderService(final PriceService priceService, final ModelMapper modelMapper) {
        this.priceService = priceService;
        this.modelMapper = modelMapper;
    }
    @Transactional
    public ResponsePrice getPriceByDateTime(RequestPrice request) {
        Optional<Price> price = priceService.getPriceByDateTime(request.getCurrentDate() , request.getProduct() , request.getBrand());
        return convertToDto(price.get(), request.getCurrentDate());

    }

    private ResponsePrice convertToDto(Price price, LocalDateTime currentDate) {
        ResponsePrice postDto = modelMapper.map(price, ResponsePrice.class);
        postDto.setCurrentLocalDate(currentDate);
        return postDto;
    }

}
