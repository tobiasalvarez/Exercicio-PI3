package com.hotel.exception;

public class QuartoNotFoundException extends RuntimeException {
    public QuartoNotFoundException(Long id) {
        super("Quarto não encontrado com ID: " + id);
    }
}
