package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.entity.Price;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService (final PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    @Transactional
    public Optional<Price> getPriceByDateTime(LocalDateTime priceDate, Integer product, Integer brand) {
        return priceRepository.getPriceByDateTime(priceDate, product, brand);
    }


}
