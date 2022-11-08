package com.martinsladek.example.springeton.student;

import com.martinsladek.example.springeton.exceptions.entityconflict.ConflictStudentLessonExists;
import com.martinsladek.example.springeton.exceptions.entityconflict.ConflictStudentLessonNotExists;
import com.martinsladek.example.springeton.lesson.Lesson;
import com.martinsladek.example.springeton.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Isolation;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentLessonService {
    @Autowired
    StudentService studentService;

    @Autowired
    LessonService lessonService;

//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void addStudentLesson(Long studentId, Long lessonId) {
        Student student = studentService.findById(studentId);
        Lesson lesson = lessonService.findById(lessonId);

        if (student.getLessons().contains(lesson)) {
            throw new ConflictStudentLessonExists(studentId, lessonId);
        }

        student.getLessons().add(lesson);
        studentService.save(student);
    }

//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = -1, readOnly = true, rollbackFor = { RuntimeException.class })
//    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void removeStudentLesson(Long studentId, Long lessonId) {
        Student student = studentService.findById(studentId);
        Lesson lesson = lessonService.findById(lessonId);

        if (! student.getLessons().contains(lesson)) {
            throw new ConflictStudentLessonNotExists(studentId, lessonId);
        }

        student.getLessons().remove(lesson);
        studentService.save(student);
    }
}
