package com.idtx.price.infraestructure.service.rest;

import com.idtx.price.application.service.PriceFinderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(classes = IdtxApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(value = {PriceController.class, PriceFinderService.class},  excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class PriceControllerRestTest {


    @Autowired
    private MockMvc mvc;


    @Test
    public void testAllEmployees() throws Exception {
        ResultActions resultActions = mvc.perform(get("/price").contentType(MediaType.APPLICATION_JSON)
                .param("currentDate", "2020-06-16T01:30:00.000-05:00")
                .param("product", "35455")
                .param("brand", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
