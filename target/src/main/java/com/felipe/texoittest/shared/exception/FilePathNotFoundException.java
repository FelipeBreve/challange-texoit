package com.felipe.texoittest.shared.exception;

public class FilePathNotFoundException extends RuntimeException {

    public FilePathNotFoundException(String message) {
        super(message);
    }

    public FilePathNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
