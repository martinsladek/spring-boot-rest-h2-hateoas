package com.martinsladek.example.springeton.exceptions.entityconflict;

import com.martinsladek.example.springeton.exceptions.entityconflict.ConflictException;

public class ConflictStudentLessonExists extends ConflictException {
    public ConflictStudentLessonExists(Long studentId, Long lessonId) {
        super("Student '" + studentId + "' already exists in Lesson '" + lessonId + "'.");
    }
}
