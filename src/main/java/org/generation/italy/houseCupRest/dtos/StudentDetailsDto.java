package org.generation.italy.houseCupRest.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDetailsDto {
    private long id;
    private String firstname;
    private String surname;
    private String dob;
    private char sex;
    private String mail;
    private String phone;
    private String background;
    private String education;
    private boolean isPref;
    private CourseDto course;
    private HouseDto house;
    private int totalScore;

    public StudentDetailsDto() {}
    public StudentDetailsDto(long id, String firstname, String surname, String dob, char sex, String mail,
                             String phone, String background, String education, boolean isPref, CourseDto course,
                             HouseDto house, int totalScore) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob;
        this.sex = sex;
        this.mail = mail;
        this.phone = phone;
        this.background = background;
        this.education = education;
        this.isPref = isPref;
        this.course = course;
        this.house = house;
        this.totalScore = totalScore;
    }
    public Student toStudent(){
        return new Student(this.id,this.firstname,this.surname,
                LocalDate.parse(this.dob),this.sex,this.mail,
                this.phone,this.background, this.education,
                this.isPref,null,null,new ArrayList<>());
    }
    public StudentDetailsDto(Student s) { //richiamiamo il costruttore canonico
        this(s.getId(),s.getFirstname(),s.getSurname(),s.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE),
                s.getSex(),s.getMail(),s.getPhone(),s.getBackground(),s.getEducation(),s.getIsPref(),new CourseDto(s.getCourse()),
                new HouseDto(s.getHouse()),s.getScores().stream().mapToInt(Score::getPoints).sum());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public boolean isPref() {
        return isPref;
    }

    public void setPref(boolean pref) {
        isPref = pref;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public HouseDto getHouse() {
        return house;
    }

    public void setHouse(HouseDto house) {
        this.house = house;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
