package com.idtx.price.application.service;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.application.dto.ResponsePrice;
import com.idtx.price.domain.entity.entity.Price;
import com.idtx.price.domain.entity.service.PriceRepository;
import com.idtx.price.domain.entity.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceFinderServiceIntegrationTest {


    private PriceService priceService;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    private ModelMapper modelMapper;

    private PriceFinderService priceFinderService;

    @Test
    void integration_happy_path_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;

        Price price1 = createPrice(1,0, BigDecimal.ONE);
        Price price2 = createPrice(2,2, BigDecimal.TEN);
        Price price3 = createPrice(3,1, BigDecimal.valueOf(5));
        List<Price> prices = Arrays.asList(price1, price2, price3);
        RequestPrice requestPrice = createRequestPrice(now, product, brand);

        priceService = new PriceService(priceRepository);
        priceFinderService = new PriceFinderService(priceService, modelMapper);

        when(priceRepository.getAllPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(prices.stream());
        when(modelMapper.map(any(), eq(ResponsePrice.class))).thenReturn(new ResponsePrice());

        ResponsePrice responsePrice = priceFinderService.getPriceByDateTime(requestPrice);
        assertThat(responsePrice).isNotNull();
        assertThat(responsePrice.getCurrentLocalDate()).isEqualTo(now);

        verify(modelMapper, atLeastOnce()).map(any(), eq(ResponsePrice.class));
        verify(priceRepository, atLeastOnce()).getAllPriceByDateTime(eq(now), eq(product), eq(brand));

    }

    private RequestPrice createRequestPrice(LocalDateTime now, int product, int brand ) {
        return RequestPrice.builder().currentDate(now).product(product).brand(brand).build();
    }

    private Price createPrice(int priceListId, int priority, BigDecimal price) {
        return Price.builder().priceList(priceListId).priority(priority).price(price).build();
    }
}