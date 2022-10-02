package com.martinsladek.example.springeton.exceptions;

public class StudentNotFoundException extends EntityNotFoundException {
    public StudentNotFoundException(Long id) {
        super("Student", id);
    }
}
