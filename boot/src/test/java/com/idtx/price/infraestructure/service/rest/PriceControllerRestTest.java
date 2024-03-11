package com.idtx.price.infraestructure.service.rest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerRestTest {


    @Autowired
    private MockMvc mvc;


    @Test
    public void testAllEmployees() throws Exception {
        ResultActions resultActions = mvc.perform(get("/api/price").contentType(MediaType.APPLICATION_JSON)
                .param("currentDate", "2020-06-16T01:30:00")
                .param("product", "35455")
                .param("brand", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
