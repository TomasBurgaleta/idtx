package com.idtx.price.application.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RequestPrice {

    private LocalDateTime currentDate;
    private Integer product;
    private Integer brand;


}
