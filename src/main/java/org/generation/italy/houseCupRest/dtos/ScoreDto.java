package org.generation.italy.houseCupRest.dtos;

import java.time.LocalDate;

public class ScoreDto {
    private long id;
    private int points;
    private String motivation;
    private LocalDate assignDate;
    private long studentId;
    private long teacherId;

    public ScoreDto(long id, int points, String motivation, LocalDate assignDate, long studentId, long teacherId, String studentFullName, String teacherFullName) {
        this.id = id;
        this.points = points;
        this.motivation = motivation;
        this.assignDate = assignDate;
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

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
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
