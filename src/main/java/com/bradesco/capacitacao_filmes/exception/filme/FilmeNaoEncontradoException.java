package com.bradesco.capacitacao_filmes.exception.filme;

public class FilmeNaoEncontradoException extends RuntimeException {
    public FilmeNaoEncontradoException(String message) {
        super(message);
    }
}
