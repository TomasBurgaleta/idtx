package com.idtx.price.infraestructure.service.rest;


import com.idtx.price.application.exception.PriceNotFoundException;
import com.idtx.price.infraestructure.dto.PriceErrorDto;
import com.idtx.price.infraestructure.exception.PriceParameterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(PriceNotFoundException.class)
    protected ResponseEntity<PriceErrorDto> handlePriceNotFoundException(
            PriceNotFoundException ex) {
        PriceErrorDto error = PriceErrorDto.builder().message(ex.getMessage())
                .exception("PriceNotFoundException").status(404).build();
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(PriceParameterException.class)
    protected ResponseEntity<PriceErrorDto> handlePriceParameterException(
            PriceParameterException ex) {
        PriceErrorDto error = PriceErrorDto.builder().message(ex.getMessage())
                .exception("PriceParameterException").status(400).build();
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(
            Exception ex) {
        PriceErrorDto error = PriceErrorDto.builder().message(ex.getMessage())
                .exception("Exception").status(500).build();
        return ResponseEntity.status(500).body(error);
    }
}
