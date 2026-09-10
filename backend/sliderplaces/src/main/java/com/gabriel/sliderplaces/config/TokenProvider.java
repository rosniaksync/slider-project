package com.gabriel.sliderplaces.config;

import com.gabriel.sliderplaces.model.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${spring.jwt.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario) {
        SecretKey chave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .issuer("sliderplaces")
                .subject(usuario.getEmail())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                .signWith(chave)
                .compact();
    }

    public String validarTokenEExtrairEmail(String token) {
        SecretKey chave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(chave)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
