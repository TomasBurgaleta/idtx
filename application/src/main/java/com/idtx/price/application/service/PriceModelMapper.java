package com.idtx.price.application.service;

import com.idtx.price.application.dto.ResponsePrice;
import com.idtx.price.domain.entity.entity.Price;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceModelMapper {

    private final ModelMapper modelMapper;

    public PriceModelMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ResponsePrice convertToDto(Price price, LocalDateTime currentDate) {
        ResponsePrice responsePrice = modelMapper.map(price, ResponsePrice.class);
        responsePrice.setCurrentLocalDate(currentDate);
        return responsePrice;
    }

}
