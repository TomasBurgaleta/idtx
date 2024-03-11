package com.idtx.price.infraestructure.service.rest;


import com.idtx.price.application.exception.PriceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<Object> handlePriceNotFoundException(
            PriceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(
            Exception ex) {
        return ResponseEntity.status(500).build();
    }
}
