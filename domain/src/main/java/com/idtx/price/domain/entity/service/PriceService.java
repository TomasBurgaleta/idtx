package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.dto.PriceDomain;
import com.idtx.price.domain.entity.entity.Price;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    private final PriceDomainMapper priceDomainMapper;

    public PriceService (final PriceRepository priceRepository, final PriceDomainMapper priceDomainMapper) {
        this.priceRepository = priceRepository;
        this.priceDomainMapper = priceDomainMapper;
    }
    @Transactional
    public Optional<PriceDomain> getPriceByDateTime(LocalDateTime priceDate, Integer product, Integer brand) {
        Optional<Price> price =  priceRepository.getPriceByDateTime(priceDate, product, brand);
        return priceDomainMapper.convertToPriceDomain(price);
    }


}
