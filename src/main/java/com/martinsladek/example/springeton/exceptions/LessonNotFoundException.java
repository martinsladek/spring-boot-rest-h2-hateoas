package com.martinsladek.example.springeton.exceptions;

public class LessonNotFoundException extends EntityNotFoundException {
    public LessonNotFoundException(Long id) {
        super("Lesson", id);
    }
}
