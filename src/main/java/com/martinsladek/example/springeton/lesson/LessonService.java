package com.martinsladek.example.springeton.lesson;

import java.util.List;
import java.util.Set;

import com.martinsladek.example.springeton.student.Student;
import com.martinsladek.example.springeton.exceptions.LessonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public Lesson findById(Long id) {
        return lessonRepository.findById(id)
                .<RuntimeException>orElseThrow( () -> { throw new LessonNotFoundException(id); } );
    }

    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void delete(Long id) {
        try {
            lessonRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new LessonNotFoundException(id);
        }
    }

    public Set<Student> getStudents(Long lessonId) {
        return findById(lessonId).getStudents();
    }
}
