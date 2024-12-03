package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;
import org.generation.italy.houseCupRest.model.entities.Teacher;

import java.time.LocalDate;

public class ScoreDto {
    private long id;
    private int score;
    private String reason;
    private long studentId, teacherId;
    private LocalDate localDate;

    public ScoreDto(){
    }

    public ScoreDto(long id, int score, String reason, long studentId, long teacherId, LocalDate localDate) {
        this.id = id;
        this.score = score;
        this.reason = reason;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.localDate = localDate;
    }

    public ScoreDto(Score s){
        this.id = s.getId();
        this.score = s.getPoints();
        this.reason = s.getMotivation();
        this.studentId = s.getStudent().getId();
        this.teacherId = s.getTeacher().getId();
        this.localDate = s.getAssign_date();
    }

    public Score toScore(){
        Score newScore = new Score();
        newScore.setId(id);
        newScore.setPoints(score);
        newScore.setMotivation(reason);
        Student student = new Student();
        student.setId(studentId);
        newScore.setStudent(student);
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        newScore.setTeacher(teacher);
        newScore.setAssign_date(localDate);
        return newScore;
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
