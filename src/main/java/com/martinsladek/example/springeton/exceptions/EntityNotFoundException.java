package com.martinsladek.example.springeton.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException (String type, String id) {
        super("Entity not found. Entity type '" + type + "', entity id '" + id + "'.");
    }

    public EntityNotFoundException (String type, Long id) {
        super("Entity not found. Entity type '" + type + "', entity id '" + id + "'.");
    }
}
