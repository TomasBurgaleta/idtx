package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.dto.PriceDomain;
import com.idtx.price.domain.entity.entity.Price;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PriceDomainMapper {
    private final ModelMapper modelMapper;

    public PriceDomainMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Optional<PriceDomain> convertToPriceDomain(Price price) {
        if(Objects.nonNull(price)) {
            return Optional.of(modelMapper.map(price, PriceDomain.class));
        }
        return Optional.empty();

    }
}
