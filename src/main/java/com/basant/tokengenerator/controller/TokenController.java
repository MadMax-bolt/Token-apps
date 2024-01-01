package com.basant.tokengenerator.controller;

import com.basant.tokengenerator.model.JWKResponseList;
import com.basant.tokengenerator.model.RequestToken;
import com.basant.tokengenerator.model.ResponseToken;
import com.basant.tokengenerator.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping(value = "/publicKey",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<JWKResponseList> getPublicKey() {
        try {
            JWKResponseList jwkResponseList = tokenService.getPublicKey();
            return ResponseEntity.ok(jwkResponseList);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping(value = "/token",
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<ResponseToken> signToken(@RequestBody RequestToken requestToken) {
        try {
            return ResponseEntity.ok(tokenService.getToken(requestToken)
            );

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}