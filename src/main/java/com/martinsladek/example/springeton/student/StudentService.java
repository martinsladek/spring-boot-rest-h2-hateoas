package com.martinsladek.example.springeton.student;

import java.util.List;
import java.util.Set;

import com.martinsladek.example.springeton.lesson.Lesson;
import com.martinsladek.example.springeton.exceptions.entitynotfound.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    Student findById (Long studentId) {
        return studentRepository.findById(studentId)
                .<RuntimeException>orElseThrow( () -> { throw new StudentNotFoundException(studentId); } );
    }

    List<Student> findAll() {
        return  studentRepository.findAll();
    }

    Student save(Student student) {
        return studentRepository.save(student);
    }

    void delete(Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new StudentNotFoundException(id);
        }
    }

    Set<Lesson> findLessons(Long studentId) {
        Student student = findById(studentId);
        return student.getLessons();
    }
}
