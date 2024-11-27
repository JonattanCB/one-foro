package com.aluracurso.one_foro.infra.segurity;

import com.aluracurso.one_foro.domain.autores.Autor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secrete}")
    String apiToken;
    public String generateToken(Autor autor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiToken);
            return  JWT.create()
                    .withIssuer("one foro")
                    .withSubject(autor.getEmail())
                    .withClaim("id", autor.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new IllegalArgumentException(exception);
        }
    }

    public  String getSubject(String token){
        if (token == null){
            throw new RuntimeException("null token");
        }
        DecodedJWT verifier =null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiToken);
            verifier=  JWT.require(algorithm)
                    .withIssuer("one foro")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            System.out.printf("error:" +exception.getMessage());
        }
        if(verifier.getSubject() == null) {
            throw new ValidationException("verifier null");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
