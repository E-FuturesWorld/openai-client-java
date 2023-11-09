package com.efutures.exceptio;

public class NoChoiceException extends RuntimeException{
    public NoChoiceException(String message) {
        super(message);
    }

    public NoChoiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
