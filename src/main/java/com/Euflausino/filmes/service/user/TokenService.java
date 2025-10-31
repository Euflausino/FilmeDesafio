package com.Euflausino.filmes.service.user;

import com.Euflausino.filmes.entities.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret); // cria um algoritimo com uma senha
        return JWT.create() // cria um java web tokewn
                .withIssuer("Euflausino") // Marca a API dona da chave
                .withSubject(user.getUsername()) // passo o usuario associado ao token jwt pelo username
                .withExpiresAt(dataDeExpiracao)
                .sign(algorithm); // marca o jwt com o algoritimo
    }

    private final Instant dataDeExpiracao = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

}
