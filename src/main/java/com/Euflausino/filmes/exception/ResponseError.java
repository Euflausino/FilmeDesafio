package com.Euflausino.filmes.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ResponseError(
        String message,
        int status,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime data
) {
    public ResponseError(String message, int status){
        this( message, status, LocalDateTime.now() );
    }
}
