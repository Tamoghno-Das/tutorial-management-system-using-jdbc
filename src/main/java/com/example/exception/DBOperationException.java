package com.example.exception;

public class DBOperationException extends Exception {
    public DBOperationException(String message) {
        super(message);
    }
}
