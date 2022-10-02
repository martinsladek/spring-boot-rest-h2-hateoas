package com.martinsladek.example.springeton.student;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.martinsladek.example.springeton.lesson.Lesson;
import com.martinsladek.example.springeton.lesson.LessonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public EntityModel<Student> one(@PathVariable(value = "id") String id) {
        Student student = studentService.findById(Long.parseLong(id, 10));

        return EntityModel.of(student,
                linkTo(methodOn(StudentController.class).one(id)).withSelfRel(),
                linkTo(methodOn(StudentController.class).all()).withRel("students")
        );
    }

    @GetMapping("all")
    public CollectionModel<EntityModel<Student>> all() {
        List<EntityModel<Student>> students = studentService.findAll().stream()
                .map(student -> EntityModel.of(student,
                        linkTo(methodOn(StudentController.class).one(String.valueOf(student.getId()))).withSelfRel(),
                        linkTo(methodOn(StudentController.class).all()).withRel("students")))
                .collect(Collectors.toList());

        return CollectionModel.of(students,
                linkTo(methodOn(StudentController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}/lesson/all")
    public CollectionModel<EntityModel<Lesson>> getLessons(@PathVariable(value = "id") String id) {
        List<EntityModel<Lesson>> lessons = studentService.findLessons(Long.parseLong(id, 10)).stream()
                .map(lesson -> EntityModel.of(lesson,
                        linkTo(methodOn(LessonController.class).one(lesson.getId())).withSelfRel(),
                        linkTo(methodOn(LessonController.class).all()).withRel("lesson/all")))
                .collect(Collectors.toList());

        return CollectionModel.of(lessons,
                linkTo(methodOn(StudentController.class).getLessons(id)).withSelfRel());
    }
}
