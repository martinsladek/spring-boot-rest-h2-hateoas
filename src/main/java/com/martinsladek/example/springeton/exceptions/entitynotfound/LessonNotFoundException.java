package com.martinsladek.example.springeton.exceptions.entitynotfound;

public class LessonNotFoundException extends EntityNotFoundException {
    public LessonNotFoundException(Long id) {
        super("Lesson", id);
    }
}
