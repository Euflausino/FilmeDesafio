package com.bradesco.capacitacao_filmes.exception;

import com.bradesco.capacitacao_filmes.exception.filme.FilmeNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FilmeNaoEncontradoException.class)
    public ResponseEntity<ResponseError> filmeNaoEncontrado(FilmeNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGenericException(Exception ex) {
        return ResponseEntity.internalServerError().body(new ResponseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        return ResponseEntity.badRequest().body(new ResponseError(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST.value()));
    }

}
