package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.entity.Price;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
@Service
public class PriceService {

    private final PriceRepository priceRepository;

    public PriceService (final PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    @Transactional
    public Optional<Price> getPriceByDateTime(LocalDateTime priceDate, Integer product, Integer brand) {
        Stream<Price> prices = priceRepository.getAllPriceByDateTime(priceDate, product, brand);
        return prices.min(priorityComparator);
    }

    private Comparator<Price> priorityComparator = new Comparator<Price>() {
        @Override
        public int compare(Price i1, Price i2) {
            if(Objects.isNull(i2) || Objects.isNull(i2.getPriority())) return 1;
            if(Objects.isNull(i1) || Objects.isNull(i1.getPriority())) return -1;
            return i2.getPriority().compareTo(i1.getPriority());
        }
    };
}
