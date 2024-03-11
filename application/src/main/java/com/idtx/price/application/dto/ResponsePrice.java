package com.idtx.price.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class ResponsePrice {

    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private LocalDateTime currentLocalDate;
    private BigDecimal price;

}
