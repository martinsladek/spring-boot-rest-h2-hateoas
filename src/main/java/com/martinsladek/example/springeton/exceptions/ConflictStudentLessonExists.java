package com.martinsladek.example.springeton.exceptions;

public class ConflictStudentLessonExists extends ConflictException {
    public ConflictStudentLessonExists(Long studentId, Long lessonId) {
        super("Student '" + String.valueOf(studentId) + "' already exists in Lesson '" + lessonId + "'.");
    }
}
