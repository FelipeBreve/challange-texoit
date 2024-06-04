package com.felipe.texoittest.shared.exception;

public class ListEmptyToProcessException extends Exception {

    public ListEmptyToProcessException(String message) {
        super(message);
    }

    public ListEmptyToProcessException(String message, Throwable cause) {
        super(message, cause);
    }

}
