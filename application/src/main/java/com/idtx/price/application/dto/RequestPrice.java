package com.idtx.price.application.dto;

import lombok.*;


import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
public class RequestPrice {

    private LocalDateTime currentDate;
    private Integer product;
    private Integer brand;


}
