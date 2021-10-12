package com.leacar21.technical.seat.function.reservation.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ResourceNotFoundException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }
}