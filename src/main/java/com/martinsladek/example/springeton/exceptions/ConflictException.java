package com.martinsladek.example.springeton.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super("Entities conflict. " + message);
    }
}
