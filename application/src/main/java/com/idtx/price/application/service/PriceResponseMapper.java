package com.idtx.price.application.service;

import com.idtx.price.application.dto.ResponsePrice;
import com.idtx.price.domain.entity.dto.PriceDomain;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceResponseMapper {

    private final ModelMapper modelMapper;

    public PriceResponseMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ResponsePrice convertToDto(PriceDomain price, LocalDateTime currentDate) {
        ResponsePrice responsePrice = modelMapper.map(price, ResponsePrice.class);
        responsePrice.setCurrentLocalDate(currentDate);
        return responsePrice;
    }

}
