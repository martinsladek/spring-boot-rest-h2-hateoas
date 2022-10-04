package com.martinsladek.example.springeton.exceptions.entityconflict;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super("Entities conflict. " + message);
    }
}
