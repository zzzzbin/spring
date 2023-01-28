package com.example.demo.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ValidateParametersControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void whenPathVariableIsInvalid_thenReturnsStatus400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validatePathVariable/3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenRequestParameterIsInvalid_thenReturnsStatus400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/validateRequestParameter")
                .param("param", "3"))
                .andExpect(status().isBadRequest());
    }

}