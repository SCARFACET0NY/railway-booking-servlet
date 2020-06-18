package com.anton.railway.booking.exception;

public class WagonTypeException extends RuntimeException {
    public WagonTypeException(String message) {
        super(message);
    }

    public WagonTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
