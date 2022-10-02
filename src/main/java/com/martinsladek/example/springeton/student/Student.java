package com.martinsladek.example.springeton.student;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.martinsladek.example.springeton.lesson.Lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student")
public class Student {
    @Id
    // Generation Types: AUTO, IDENTITY, SEQUENCE and TABLE
    // https://www.baeldung.com/hibernate-identifiers#generated-identifiers
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active;
    private int credits;

    @ManyToMany
    @JoinTable(
            name = "student_lesson",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    @JsonIgnore
    // Other annotations:
    // @JsonManagedReference (visible from this side)
    // @JsonBackReference (hidden from this side)
    private Set<Lesson> lessons;

    public Student() {
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }
}
