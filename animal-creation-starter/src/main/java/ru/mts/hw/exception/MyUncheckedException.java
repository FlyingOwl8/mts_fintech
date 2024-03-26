package ru.mts.hw.exception;

public class MyUncheckedException extends IllegalArgumentException {
    public final String message;

    public MyUncheckedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
