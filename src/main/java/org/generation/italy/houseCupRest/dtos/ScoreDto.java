package org.generation.italy.houseCupRest.dtos;
import org.generation.italy.houseCupRest.model.entities.Score;

import java.time.LocalDate;

public class ScoreDto {
    private int score;
    private String reason;
    private long studentId, teacherId;
    private LocalDate date;

    public ScoreDto() {
    }
    public ScoreDto(int score, String reason, long studentId, long teacherId, LocalDate date) {
        this.score = score;
        this.reason = reason;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.date = date;
    }
    public ScoreDto(Score s){
        this.score = s.getPoints();
        this.reason = s.getMotivation();
        this.studentId = s.getStudent().getId();
        this.teacherId = s.getTeacher().getId();
        this.date = s.getAssign_date();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
