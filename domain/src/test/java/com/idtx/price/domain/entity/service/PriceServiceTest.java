package com.idtx.price.domain.entity.service;

import com.idtx.price.domain.entity.dto.PriceDomain;
import com.idtx.price.domain.entity.entity.Price;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceDomainMapper priceDomainMapper;

    @InjectMocks
    private PriceService priceService;

    @Test
    void checkVoid_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;
        when(priceRepository.getPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(Optional.empty());
        when(priceDomainMapper.convertToPriceDomain(Optional.empty())).thenReturn(Optional.empty());
        Optional<PriceDomain> result = priceService.getPriceByDateTime(now, product, brand);

        assertThat(result).isEmpty();
        verify(priceRepository, atLeastOnce()).getPriceByDateTime(eq(now), eq(product), eq(brand));
        verify(priceDomainMapper, atLeastOnce()).convertToPriceDomain(any());

    }


    @Test
    void checkPriority_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;
        Price price2 = createPrice(2,2, BigDecimal.TEN);
        PriceDomain priceDomain = PriceDomain.builder().priority(2).price(BigDecimal.TEN).priceList(2).build();

        when(priceRepository.getPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(Optional.of(price2));
        when(priceDomainMapper.convertToPriceDomain(any())).thenReturn(Optional.of(priceDomain));

        Optional<PriceDomain> result = priceService.getPriceByDateTime(now, product, brand);

        assertThat(result).isNotEmpty();
        assertThat(result.get().getPriority()).isEqualTo(2);
        assertThat(result.get().getPrice()).isEqualTo(BigDecimal.TEN);

        verify(priceRepository, atLeastOnce()).getPriceByDateTime(eq(now), eq(product), eq(brand));


    }

    private Price createPrice(int priceListId, int priority, BigDecimal price) {
        return Price.builder().priceList(priceListId).priority(priority).price(price).build();
    }
}