package com.martinsladek.example.springeton.exceptions.entitynotfound;

public class StudentNotFoundException extends EntityNotFoundException {
    public StudentNotFoundException(Long id) {
        super("Student", id);
    }
}
