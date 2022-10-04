package com.martinsladek.example.springeton.student;

import com.martinsladek.example.springeton.lesson.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/lesson")
public class StudentLessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    StudentLessonService studentLessonService;

    @PutMapping("/{lessonId}/student/{studentId}")
    public void addStudentLesson(@PathVariable(value = "lessonId") Long lessonId, @PathVariable(value = "studentId") Long studentId) {
        studentLessonService.addStudentLesson(studentId, lessonId);
    }

    @DeleteMapping("/{lessonId}/student/{studentId}")
    public ResponseEntity<HttpStatus> removeStudentLesson(@PathVariable(value = "lessonId") Long lessonId, @PathVariable(value = "studentId") Long studentId) {
        studentLessonService.removeStudentLesson(studentId, lessonId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/student/all")
    public CollectionModel<EntityModel<Student>> getStudentLesson(@PathVariable(value = "id") Long id) {
        List<EntityModel<Student>> students = lessonService.getStudents(id).stream()
                .map(student -> EntityModel.of(student,
                        WebMvcLinkBuilder.linkTo(methodOn(StudentController.class).one(String.valueOf(student.getId()))).withSelfRel(),
                        linkTo(methodOn(StudentController.class).all()).withRel("student/all")))
                .collect(Collectors.toList());

        return CollectionModel.of(students,
                linkTo(methodOn(StudentLessonController.class).getStudentLesson(id)).withSelfRel());

    }
}
