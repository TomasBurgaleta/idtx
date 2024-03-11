package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.exception.PriceNotFoundException;
import com.idtx.price.application.service.PriceFinderService;
import com.idtx.price.application.dto.RequestPrice;
import com.idtx.price.application.dto.ResponsePrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api")
public class PriceController {

    private final Logger log = LoggerFactory.getLogger(PriceController.class);
    private final PriceFinderService priceFinderService;
    private final RequestPriceCreateService requestPriceCreateService;


    public PriceController(final PriceFinderService priceFinderService) {
        this.priceFinderService = priceFinderService;
        this.requestPriceCreateService = new RequestPriceCreateService();
    }

    @GetMapping("/price")
    public ResponseEntity<ResponsePrice> getPrice(@RequestParam(value = "currentDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime currentDate, @RequestParam(value = "product")  Integer product, @RequestParam(value = "brand") Integer brand) throws PriceNotFoundException {
        RequestPrice request = requestPriceCreateService.createRequestPrice(currentDate, product, brand);
        log.info("reading request {} from price api", request);
        return ResponseEntity.ok().body(priceFinderService.getPriceByDateTime(request));
    }



}
