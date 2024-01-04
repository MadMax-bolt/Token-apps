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
        JWKResponseList jwkResponseList = tokenService.getPublicKey();

        assertEquals(1, jwkResponseList.getKeys().size());
    }

    @Test
    public void testSignToken() throws ParseException, JOSEException {
        // Test the signToken method in the service
        String signedToken = tokenService.signToken(UUID.randomUUID().toString());

        assertNotNull(signedToken);
        assertFalse(signedToken.isEmpty(), "Token should not be empty");
    }

}
