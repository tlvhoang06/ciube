package com.hoang.ciube.modules.auth.service;

import com.hoang.ciube.modules.auth.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.valid-duration}")
    private long validDuration;


    @Value("${jwt.signer-key}")
    private String secretKey;

    public String generateToken(User user) {
        Instant now = Instant.now();

        // create header
        JWSHeader header = new JWSHeader
                .Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();

        // create claim
        Date issueAt = Date.from(now);
        Date expiryTime = Date.from(now.plusSeconds(validDuration));
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("role", "USER")
                .issueTime(issueAt)
                .expirationTime(expiryTime)
                .build();

        // sign jwt
        SignedJWT signedJWT = new SignedJWT(header, claims);
        try {
            JWSSigner signer = new MACSigner(secretKey);
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            throw new IllegalStateException("Fail to generate JWT", e);
        }

        return signedJWT.serialize();
    }

    public boolean validateToken(String token) throws JOSEException, ParseException {
        // parse token
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(secretKey);

        if(signedJWT.verify(verifier)){
            long expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime().getTime();
            long now = System.currentTimeMillis();
            return (expiryTime > now);
        }

        return false;
    }


}
