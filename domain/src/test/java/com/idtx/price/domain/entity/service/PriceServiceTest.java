package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.entity.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void checkVoid_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;
        when(priceRepository.getAllPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(Stream.empty());
        Optional<Price> result = priceService.getPriceByDateTime(now, product, brand);

        assertThat(result).isEmpty();
        verify(priceRepository, atLeastOnce()).getAllPriceByDateTime(eq(now), eq(product), eq(brand));

    }


    @Test
    void checkPriority_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;
        Price price1 = createPrice(1,0, BigDecimal.ONE);
        Price price2 = createPrice(2,2, BigDecimal.TEN);
        Price price3 = createPrice(3,1, BigDecimal.valueOf(5));
        List<Price> prices = Arrays.asList(price1, price2, price3);


        when(priceRepository.getAllPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(prices.stream());
        Optional<Price> result = priceService.getPriceByDateTime(now, product, brand);

        assertThat(result).isNotEmpty();
        assertThat(result.get().getPriority()).isEqualTo(2);
        assertThat(result.get().getPrice()).isEqualTo(BigDecimal.TEN);

        verify(priceRepository, atLeastOnce()).getAllPriceByDateTime(eq(now), eq(product), eq(brand));


    }

    private Price createPrice(int priceListId, int priority, BigDecimal price) {
        return Price.builder().priceList(priceListId).priority(priority).price(price).build();
    }
}