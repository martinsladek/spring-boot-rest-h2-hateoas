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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
//    The same as:
//    @RequestMapping(method = RequestMethod.GET
//    CORS configuration:
//    @CrossOrigin(origins = "http://localhost:8080")
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

    @PostMapping("")
    public ResponseEntity<Student> create(@RequestBody Student newStudent) {
        Student student = studentService.save(new Student(newStudent.getName(), newStudent.isActive(), newStudent.getCredits()));
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @RequestBody Student studentUpdated) {
        Student student = studentService.findById(id);

        student.setName(studentUpdated.getName());
        student.setActive(studentUpdated.isActive());
        student.setCredits(studentUpdated.getCredits());

        return new ResponseEntity<Student>(studentService.save(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        studentService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
