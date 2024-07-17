package com.inarixdono.forohub.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.inarixdono.forohub.domain.user.User;

@Service
public class TokenService {

    @Value("${api.secret.key}")
    private String key;

    public JWTDto generateJWT(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String jwt = JWT.create()
                    .withIssuer("forohub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(gerenateExpirationTime())
                    .sign(algorithm);
            return new JWTDto(jwt);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error creating token");
        }
    }

    public String getSubject(String token) {
        Algorithm algorithm = Algorithm.HMAC256(key);
        DecodedJWT verifier = JWT.require(algorithm)
                .withIssuer("forohub")
                .build()
                .verify(token);

        return verifier.getSubject();

    }

    private Instant gerenateExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
    }
}
