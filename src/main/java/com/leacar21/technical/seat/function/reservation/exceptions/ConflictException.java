package com.leacar21.technical.seat.function.reservation.exceptions;

public class ConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConflictException(String message, Object... args) {
        super(String.format(message, args));
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }

    public ConflictException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }
}