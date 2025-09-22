package com.idtx.price.application.service;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.application.dto.ResponsePrice;
import com.idtx.price.application.exception.PriceNotFoundException;
import com.idtx.price.domain.entity.dto.PriceDomain;
import com.idtx.price.domain.entity.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceFinderServiceTest {

    @Mock
    private  PriceService priceService;
    @Mock
    private PriceResponseMapper priceResponseMapper;
    @InjectMocks
    private PriceFinderService priceFinderService;

    @Test
    void getPriceByDateTime_happy_path_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;
        RequestPrice requestPrice = createRequestPrice(now, product, brand);
        PriceDomain price = createPrice(1,0, BigDecimal.ONE);
        Optional<PriceDomain> priceOptional = Optional.of(price);
        ResponsePrice response = ResponsePrice.builder().priceList(1).currentLocalDate(now).build();
        when(priceService.getPriceByDateTime(now, product, brand)).thenReturn(priceOptional);
        when(priceResponseMapper.convertToDto(price, now)).thenReturn(response);

        ResponsePrice responsePrice = priceFinderService.getPriceByDateTime(requestPrice);

        assertThat(responsePrice).isNotNull();
        assertThat(responsePrice.getCurrentLocalDate()).isEqualTo(now);

        verify(priceService, atLeastOnce()).getPriceByDateTime(eq(now), eq(product), eq(brand));
        verify(priceResponseMapper, atLeastOnce()).convertToDto(price, now);

    }

    @Test
    void getPriceByDateTime_not_result_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;
        RequestPrice requestPrice = createRequestPrice(now, product, brand);
        Optional<PriceDomain> priceOptional = Optional.empty();

        when(priceService.getPriceByDateTime(now, product, brand)).thenReturn(priceOptional);
        PriceNotFoundException thrown =  assertThrows(
                PriceNotFoundException.class,
                () -> priceFinderService.getPriceByDateTime(requestPrice)
        );

        verify(priceService, atLeastOnce()).getPriceByDateTime(eq(now), eq(product), eq(brand));
        verify(priceResponseMapper, never()).convertToDto(any(), any());


    }
    private RequestPrice createRequestPrice(LocalDateTime now, int product, int brand ) {
        return RequestPrice.builder().currentDate(now).product(product).brand(brand).build();
    }

    private PriceDomain createPrice(int priceListId, int priority, BigDecimal price) {
        return PriceDomain.builder().priceList(priceListId).priority(priority).price(price).build();
    }
}