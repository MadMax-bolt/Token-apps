package com.basant.tokengenerator.service;

import com.basant.tokengenerator.model.JWKResponseList;
import com.nimbusds.jose.JOSEException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TokenServiceTest {


    @Autowired
    private TokenService tokenService;

    @Test
    public void testGetPublicKey() throws ParseException {
        // Test the getPublicKey method in the service
        JWKResponseList jwkResponseList = tokenService.getPublicKey();

        // Perform assertions on the actual service output
        assertEquals(1, jwkResponseList.getKeys().size()); // Ensure the list contains a single key
        // Add more assertions as needed to validate the structure and content of the returned JWKResponseList
    }

    @Test
    public void testSignToken() throws ParseException, JOSEException {
        // Test the signToken method in the service
        String signedToken = tokenService.signToken(UUID.randomUUID().toString());

        assertNotNull(signedToken); // Ensure the token is not null
        assertFalse(signedToken.isEmpty(), "Token should not be empty"); // Ensure the token is not empty
    }

}
