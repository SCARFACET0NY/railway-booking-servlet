package com.anton.railway.booking.exception;

/**
 * Database connection Exception
 */
public class DataBaseConnectionException extends RuntimeException {
    public DataBaseConnectionException(String message) {
        super(message);
    }

    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
