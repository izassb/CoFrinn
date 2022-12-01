package com.example.course.model.exception;

public class ErroAutentificacaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ErroAutentificacaoException(String msg) {
        super(msg);
    }
}