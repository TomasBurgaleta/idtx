package com.idtx.price.steps;

import com.idtx.price.CucumberSpringContextConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

@CucumberContextConfiguration
public class PriceSteps  extends CucumberSpringContextConfiguration {

    private ResponseEntity<String> response;


    private static final String API = "/api/";
    private static final String CURRENT_DATE = "currentDate";
    private static final String PRODUCT = "product";
    private static final String BRAND = "brand";
    private static final int MES = 6;
    private static final int ANIO  = 2020;

    private String url = "";
    private String apiName = "";

    @Given("peticion a la API {string}")
    public void adminUserWantsToLogin(String apiName) {
        this.apiName = apiName;
    }

    @When("a las {int}:{int} del día {int} para el producto {int} para el brand {int}")
    public void theUserTriesToLoginAsAdmin(int hora, int minuto, int dia, int productoId, int brandId) {
        LocalDateTime momento = LocalDateTime.of(ANIO, MES, dia, hora, minuto);
        String url = creteUrl(momento, productoId, brandId);
        response = testRestTemplate.getForEntity(url, String.class);
        //response = testRestTemplate.getForEntity("/price?currentDate=2020-06-16T01:30:00.000-05:00&product=35455&brand=1", String.class);
    }

    @Then("nos devuelve un {int} con datos")
    public void theUserIsAllowedToUseTheApp(int httpStatus) {
        Assertions.assertEquals(httpStatus, response.getStatusCode().value());
    }


    private String creteUrl(LocalDateTime momento,  int productoId, int brandId) {
        StringBuilder sb = new StringBuilder(API);
        sb.append(apiName).append('?').append(CURRENT_DATE).append('=').append(momento.toString())
                .append('&').append(PRODUCT).append('=').append(productoId)
                .append('&').append(BRAND).append('=').append(brandId);
        return sb.toString();

    }
}
