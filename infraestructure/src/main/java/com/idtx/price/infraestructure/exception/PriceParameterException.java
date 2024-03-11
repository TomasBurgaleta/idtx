package com.idtx.price.infraestructure.exception;

public class PriceParameterException extends RuntimeException{
    public PriceParameterException(String errorMessage) {
        super(errorMessage);
    }
}
