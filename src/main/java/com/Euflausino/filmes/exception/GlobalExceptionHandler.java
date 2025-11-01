package com.Euflausino.filmes.exception;

import com.Euflausino.filmes.exception.filme.FilmeNaoEncontradoException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

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

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseError> handleMethodUsernameNotFoundException(UsernameNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseError> handleConstraintViolationException(ConstraintViolationException ex){
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseError> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseError> handleBadCredentialsException(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseError(ex.getMessage(), HttpStatus.UNAUTHORIZED.value()));
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ResponseError> handleJWTCreationException(JWTCreationException ex){
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseError> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.internalServerError().body(new ResponseError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ResponseError> handleJWTVerificationException(JWTVerificationException ex){
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ResponseError> handleHttpClientErrorException(HttpClientErrorException.Forbidden ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseError(ex.getMessage(), HttpStatus.FORBIDDEN.value()));
    }

}
