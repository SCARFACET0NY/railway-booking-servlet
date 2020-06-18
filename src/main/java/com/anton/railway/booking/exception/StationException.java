package com.anton.railway.booking.exception;

public class StationException extends RuntimeException {
    public StationException(String message) {
        super(message);
    }

    public StationException(String message, Throwable cause) {
        super(message, cause);
    }
}
