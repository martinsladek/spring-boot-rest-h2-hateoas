package com.martinsladek.example.springeton.student;

import java.util.Set;

import javax.persistence.*;

import com.martinsladek.example.springeton.lesson.Lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "student")
public class Student {
    @Id
    // Generation Types: AUTO, IDENTITY, SEQUENCE and TABLE
    // https://www.baeldung.com/hibernate-identifiers#generated-identifiers
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

/*
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "student_sequence"),
                    @Parameter(name = "initial_value", value = "100"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
*/

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_generator")
//    @SequenceGenerator(name="student_generator", sequenceName = "student_seq", allocationSize=50)
    private Long id;
    private String name;
    private boolean active;
    private int credits;

    @ManyToMany(fetch = FetchType.LAZY)
//    Optionally define cascade:
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
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

    public Student(String name, boolean active, int credits) {
        this.name = name;
        this.active = active;
        this.credits = credits;
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
