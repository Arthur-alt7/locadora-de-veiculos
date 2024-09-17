package com.example.LocadoraDeVeiculos.exception;

public class EmptyClientListException extends RuntimeException {
    public EmptyClientListException(String message) {
        super(message);
    }
}
