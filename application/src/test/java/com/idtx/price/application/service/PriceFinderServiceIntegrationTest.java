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

        Price price2 = createPrice(2,2, BigDecimal.TEN);
        RequestPrice requestPrice = createRequestPrice(now, product, brand);

        PriceService priceService = new PriceService(priceRepository);
        PriceModelMapper priceModelMapper = new PriceModelMapper(modelMapper);
        PriceFinderService priceFinderService = new PriceFinderService(priceService, priceModelMapper);

        when(priceRepository.getPriceByDateTime(eq(now), eq(product), eq(brand))).thenReturn(Optional.of(price2));
        when(modelMapper.map(any(), eq(ResponsePrice.class))).thenReturn(new ResponsePrice());

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