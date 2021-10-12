package com.leacar21.technical.seat.function.reservation.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Object... args) {
        super(String.format(message, args));
    }

    public BadRequestException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }
}