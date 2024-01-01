package com.basant.tokengenerator.controller;

import com.basant.tokengenerator.model.RequestToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TokenApApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    private static final String BASIC_AUTH = "Basic MTIzOjEyMw==";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testGetPublicKeyEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/publicKey")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
        // Add more assertions as needed for the response content
    }

    @Test
    @SneakyThrows
    public void testSignTokenEndpoint() throws Exception {
        String requestBody = "{\"value\": \"basant\"}"; // Replace with your request JSON
        mockMvc.perform(post("/token")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        String pai = UUID.randomUUID().toString();
        RequestToken request = new RequestToken(pai);
        this.mockMvc.perform(post("/token")
						.header(HttpHeaders.AUTHORIZATION, BASIC_AUTH)
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(request))
                ).andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();
    }

}
