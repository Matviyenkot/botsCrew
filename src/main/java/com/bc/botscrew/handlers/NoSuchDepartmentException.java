package com.bc.botscrew.handlers;

public class NoSuchDepartmentException extends RuntimeException{
    public NoSuchDepartmentException(String message) {
        super(message);
    }
}
