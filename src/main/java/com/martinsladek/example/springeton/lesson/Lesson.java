package com.martinsladek.example.springeton.lesson;

import java.util.Set;

import javax.persistence.*;

import com.martinsladek.example.springeton.student.Student;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    // Generation Types: AUTO, IDENTITY, SEQUENCE and TABLE
    // https://www.baeldung.com/hibernate-identifiers#generated-identifiers
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // mappedBy points to property in opposite class, i.e. Student.lessons
    @ManyToMany(mappedBy = "lessons")
    // Other annotations:
    // @JsonManagedReference (visible from this side)
    // @JsonBackReference (hidden from this side)
    @JsonIgnore
    private Set<Student> students;

    private String name;

    public Lesson() {
    }

    public Lesson(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
