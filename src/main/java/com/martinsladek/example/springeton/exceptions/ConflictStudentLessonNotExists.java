package com.martinsladek.example.springeton.exceptions;

public class ConflictStudentLessonNotExists extends ConflictException {
    public ConflictStudentLessonNotExists(Long studentId, Long lessonId) {
        super("Student '" + studentId + "' does not exist in Lesson '" + lessonId + "'.");
    }
}
