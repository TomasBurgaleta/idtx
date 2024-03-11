package com.idtx.price.application.exception;


public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
