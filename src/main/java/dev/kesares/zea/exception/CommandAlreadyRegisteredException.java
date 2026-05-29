package dev.kesares.zea.exception;

public class CommandAlreadyRegisteredException extends RuntimeException {

    public CommandAlreadyRegisteredException(String message) {
        super(message);
    }
}
