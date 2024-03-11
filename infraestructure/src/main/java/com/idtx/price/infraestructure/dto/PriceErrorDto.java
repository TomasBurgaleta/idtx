package com.idtx.price.infraestructure.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class PriceErrorDto {

    private String message;
    private String exception;
    private Integer status;
}
