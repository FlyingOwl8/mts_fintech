package ru.mts.hw.exception;

public class MyCheckedException extends Exception {
    public final String message;

    public MyCheckedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
