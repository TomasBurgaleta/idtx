package com.idtx.price.application.service;

import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.application.dto.ResponsePrice;
import com.idtx.price.domain.entity.dto.PriceDomain;
import com.idtx.price.domain.entity.entity.Price;
import com.idtx.price.domain.entity.service.PriceDomainMapper;
import com.idtx.price.domain.entity.service.PriceRepository;
import com.idtx.price.domain.entity.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceFinderServiceIntegrationTest {

    @Mock
    private PriceRepository priceRepository;
    @Mock
    private ModelMapper modelMapper;


    @Test
    void integration_happy_path_test() {
        LocalDateTime now = LocalDateTime.now();
        int product = 333;
        int brand = 1;

        Price price = createPrice(2,2, BigDecimal.TEN);
        PriceDomain priceDomain = PriceDomain.builder().priceList(2).priority(2).price(BigDecimal.TEN).build();
        RequestPrice requestPrice = createRequestPrice(now, product, brand);
        PriceDomainMapper priceDomainMapper = new PriceDomainMapper(modelMapper);
        PriceService priceService = new PriceService(priceRepository, priceDomainMapper);
        PriceResponseMapper priceResponseMapper = new PriceResponseMapper(modelMapper);
        PriceFinderService priceFinderService = new PriceFinderService(priceService, priceResponseMapper);

        when(priceRepository.getPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(Optional.of(price));
        when(modelMapper.map(any(), eq(ResponsePrice.class))).thenReturn(new ResponsePrice());
        when(modelMapper.map(any(), eq(PriceDomain.class))).thenReturn(priceDomain);

        ResponsePrice responsePrice = priceFinderService.getPriceByDateTime(requestPrice);
        assertThat(responsePrice).isNotNull();
        assertThat(responsePrice.getCurrentLocalDate()).isEqualTo(now);

        verify(modelMapper, atLeastOnce()).map(any(), eq(ResponsePrice.class));
        verify(priceRepository, atLeastOnce()).getPriceByDateTime(eq(now), eq(product), eq(brand));

    }

    private RequestPrice createRequestPrice(LocalDateTime now, int product, int brand ) {
        return RequestPrice.builder().currentDate(now).product(product).brand(brand).build();
    }

    private Price createPrice(int priceListId, int priority, BigDecimal price) {
        return Price.builder().priceList(priceListId).priority(priority).price(price).build();
    }
}