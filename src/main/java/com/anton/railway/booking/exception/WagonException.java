package com.anton.railway.booking.exception;

public class WagonException extends RuntimeException {
    public WagonException(String message) {
        super(message);
    }

    public WagonException(String message, Throwable cause) {
        super(message, cause);
    }
}
