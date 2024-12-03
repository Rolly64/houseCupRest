package org.generation.italy.houseCupRest.dtos;

import java.time.LocalDate;

public class ScoreDto {
    private long id;
    private int points;
    private String motivation;
    private LocalDate assign_date;
    private long studentId;
    private long teacherId;

    public ScoreDto(){};
    public ScoreDto(long id, int points, String motivation, LocalDate assign_date, long studentId, long teacherId) {
        this.id = id;
        this.points = points;
        this.motivation = motivation;
        this.assign_date = assign_date;
        this.studentId = studentId;
        this.teacherId = teacherId;
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

    public LocalDate getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(LocalDate assign_date) {
        this.assign_date = assign_date;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
}
