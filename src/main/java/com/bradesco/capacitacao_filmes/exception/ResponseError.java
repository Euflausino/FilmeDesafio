package com.bradesco.capacitacao_filmes.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ResponseError(
        String message,
        @JsonFormat(pattern = "dd/MM/yyyy")
        int status,
        LocalDateTime data
) {
    public ResponseError(String message, int status){
        this( message, status, LocalDateTime.now() );
    }
}
