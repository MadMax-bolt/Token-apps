package com.basant.tokengenerator.service;


import com.basant.tokengenerator.model.JWKResponse;
import com.basant.tokengenerator.model.JWKResponseList;
import com.basant.tokengenerator.model.RequestToken;
import com.basant.tokengenerator.model.ResponseToken;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {


    @Value("${jwt.keys}")
    private String key;


    public ResponseToken getToken(RequestToken request) throws JOSEException, ParseException {

        String value = request.getValue();
        ResponseToken responseToken = new ResponseToken();

        responseToken.setToken(signToken(value));

        return responseToken;
    }


    public JWKResponseList getPublicKey() throws ParseException {
        RSAKey jwk;
        jwk = RSAKey.parse(key);

        RSAKey publicKey = jwk.toPublicJWK();

        JWKResponse jwkResponse = new JWKResponse(publicKey);

        JWKResponseList jwkResponseList = new JWKResponseList();
        jwkResponseList.addKey(jwkResponse);

        return jwkResponseList;
    }

    public String signToken(String id) throws JOSEException, ParseException {

        RSAKey jwk;
        JWSSigner signer;
        jwk = RSAKey.parse(key);
        signer = new RSASSASigner(jwk);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("user")
                .claim("name", id)
                .issuer("Basant")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 100L * 10000000))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(jwk.getKeyID()).type(JOSEObjectType.JWT).build(),
                claimsSet);

        signedJWT.sign(signer);


        return signedJWT.serialize();
    }

}
    