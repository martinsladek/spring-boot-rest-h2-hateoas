package com.martinsladek.example.springeton.lesson;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.martinsladek.example.springeton.student.Student;
import com.martinsladek.example.springeton.student.StudentController;
import com.martinsladek.example.springeton.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public EntityModel<Lesson> one(@PathVariable(value = "id") Long id) {
        Lesson lesson = lessonService.findById(id);

        return EntityModel.of(lesson,
                linkTo(methodOn(LessonController.class).one(id)).withSelfRel(),
                linkTo(methodOn(LessonController.class).all()).withRel("lessons")
        );
    }

    @GetMapping("/all")
    public CollectionModel<EntityModel<Lesson>> all() {
        List<EntityModel<Lesson>> lessons = lessonService.findAll().stream()
                .map(lesson -> EntityModel.of(lesson,
                        linkTo(methodOn(LessonController.class).one(lesson.getId())).withSelfRel(),
                        linkTo(methodOn(LessonController.class).all()).withRel("lesson/all")))
                .collect(Collectors.toList());

        return CollectionModel.of(lessons,
                linkTo(methodOn(LessonController.class).all()).withSelfRel());
    }

    @PutMapping("/{lessonId}/student/{studentId}")
    public void addStudent(@PathVariable(value = "lessonId") Long lessonId, @PathVariable(value = "studentId") Long studentId) {
        studentService.addLesson(studentId, lessonId);
    }

    @GetMapping("/{id}/student/all")
    public CollectionModel<EntityModel<Student>> getStudents(@PathVariable(value = "id") Long id) {
        List<EntityModel<Student>> students = lessonService.getStudents(id).stream()
                .map(student -> EntityModel.of(student,
                        WebMvcLinkBuilder.linkTo(methodOn(StudentController.class).one(String.valueOf(student.getId()))).withSelfRel(),
                        linkTo(methodOn(StudentController.class).all()).withRel("student/all")))
                .collect(Collectors.toList());

        return CollectionModel.of(students,
                linkTo(methodOn(LessonController.class).getStudents(id)).withSelfRel());

    }
}
