package com.martinsladek.example.springeton.student;

import java.util.List;
import java.util.Set;

import com.martinsladek.example.springeton.lesson.Lesson;
import com.martinsladek.example.springeton.exceptions.ConflictStudentLessonExists;
import com.martinsladek.example.springeton.exceptions.StudentNotFoundException;
import com.martinsladek.example.springeton.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LessonService lessonService;

    Student findById (Long studentId) {
        return studentRepository.findById(studentId)
                .<RuntimeException>orElseThrow( () -> { throw new StudentNotFoundException(studentId); } );
    }

    List<Student> findAll() {
        List<Student> allStudents = studentRepository.findAll();
        return allStudents;
    }

    public void addLesson(Long studentId, Long lessonId) {
        Student student = findById(studentId);
        Lesson lesson = lessonService.findById(lessonId);

        if (student.getLessons().contains(lesson)) {
            throw new ConflictStudentLessonExists(studentId, lessonId);
        }

        student.getLessons().add(lesson);
        studentRepository.save(student);
    }

    Set<Lesson> findLessons(Long studentId) {
        Student student = findById(studentId);
        Set<Lesson> lessons = student.getLessons();
        return lessons;
    }
}
