package org.generation.italy.houseCupRest.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="score_id")
    private long id;
    private int points;
    private String motivation;
    private LocalDate assignDate;
    @ManyToOne
    @JoinColumn(name = "stud_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    public Score(){

    }

    public Score(long id, int points, String motivation, LocalDate assignDate, Student student, Teacher teacher) {
        this.id = id;
        this.points = points;
        this.motivation = motivation;
        this.assignDate = assignDate;
        this.student = student;
        this.teacher = teacher;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
