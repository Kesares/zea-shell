package dev.kesares.zea.exception;

public class DuplicateCommandException extends RuntimeException {

    public DuplicateCommandException(String message) {
        super(message);
    }
}
