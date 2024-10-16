package br.com.duckchain.exception;

public class EntidadeNaoEncontradaException extends Exception{
    public EntidadeNaoEncontradaException() {
    }
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
