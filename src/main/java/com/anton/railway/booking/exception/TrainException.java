package com.anton.railway.booking.exception;

public class TrainException extends RuntimeException {
    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }
}
