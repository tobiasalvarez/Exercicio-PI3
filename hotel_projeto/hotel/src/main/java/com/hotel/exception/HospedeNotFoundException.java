package com.hotel.exception;

public class HospedeNotFoundException extends RuntimeException {
    public HospedeNotFoundException(Long id) {
        super("Hóspede não encontrado com ID: " + id);
    }
}
