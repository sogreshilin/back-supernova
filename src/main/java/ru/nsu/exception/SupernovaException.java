package ru.nsu.exception;

public class SupernovaException extends RuntimeException {
    public SupernovaException() {
        super();
    }

    public SupernovaException(String message) {
        super(message);
    }

    public SupernovaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupernovaException(Throwable cause) {
        super(cause);
    }
}
