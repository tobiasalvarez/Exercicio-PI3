package com.hotel.exception;

public class ReservaNotFoundException extends RuntimeException {
    public ReservaNotFoundException(Long id) {
        super("Reserva não encontrada com ID: " + id);
    }
}
