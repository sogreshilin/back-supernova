package ru.nsu.exception.http;

import ru.nsu.exception.SupernovaException;

public class FileProcessingException extends SupernovaException {
    public FileProcessingException(String message) {
        super(message);
    }
}
