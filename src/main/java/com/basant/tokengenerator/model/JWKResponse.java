package com.basant.tokengenerator.model;

import java.util.Date;

import com.nimbusds.jose.jwk.RSAKey;

import lombok.Data;

@Data
public class JWKResponse {

//    @JsonProperty(value = "kty")
//    @Schema(example = "RSA")
    private String keyType;

//    @JsonProperty(value = "e")
//    @Schema(example = "AQAB")
    private String publicExponent;

//    @JsonProperty(value = "use")
//    @Schema(example = "sig")
    private String useFor;

//    @JsonProperty(value = "kid")
//    @Schema(example = "1945ca48-c6cb-4e72-850e-bac547ef62b3")
    private String keyId;

//    @JsonProperty(value = "iat")
//    @Schema(example = "2023-01-18T09:09:42.000+00:00")
    private Date issuedAt;

//    @JsonProperty(value = "n")
//    @Schema(example = "syizMs6eewDAUDq9Pj5-ZgrklVR3-WiN6FUUizRceghdIPTnUuMSR9O3CIF1yYkKEkjGTTc9FcvsjdhNl1SYmg-uwe5zapqf21oter-m5FpeVfwH3XcjjWPR9FdAcOb3kkrhPaYmSytzZiFgWTdlQ1jjPHuWEA-oQNfFSubausNKesxkDTNGhZNe4Z4Ch_ugrQzUomy1JiD0JcARPbNGjsYJXqEH5AfrvrDEEYIsabGazXzPIX7yHSjYbr8w")
    private String modulus;

    public JWKResponse(RSAKey rsaKey) {
        this.keyType = String.valueOf(rsaKey.getKeyType());
        this.publicExponent = (String.valueOf(rsaKey.getPublicExponent()));
        this.useFor = "sig";
        this.keyId = rsaKey.getKeyID();
        this.issuedAt = rsaKey.getIssueTime();
        this.modulus = String.valueOf(rsaKey.getModulus());

    }

}