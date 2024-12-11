package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Course;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.entities.Score;
import org.generation.italy.houseCupRest.model.entities.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class StudentDetailDto {
    private long id;
    private String firstname;
    private String surname;
    private String dob;
    private char sex;
    private String mail;
    private String phone;
    private String background;
    private String education;
    private Boolean isPref;
    private long courseId;
    private long houseId;
    private int totalScores;

    public StudentDetailDto() {}
    public StudentDetailDto(Student s) {
        this.id = s.getId();
        this.firstname = s.getFirstname();
        this.surname = s.getSurname();
        this.dob = s.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.sex = s.getSex();
        this.mail = s.getMail();
        this.phone = s.getPhone();
        this.background = s.getBackground();
        this.education = s.getEducation();
        this.isPref = s.getIsPref();
        this.courseId = s.getCourse().getId();
        this.houseId = s.getHouse().getId();
        this.totalScores = s.getScores().stream().mapToInt(Score::getPoints).sum();
    }
    public StudentDetailDto(long id, String firstname, String surname, String dob, char sex, String mail, String phone,
                            String background, String education, Boolean isPref, long courseId, long houseId, int totalScores) {
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
        this.courseId = courseId;
        this.houseId = houseId;
        this.totalScores = totalScores;
    }

    public Student toStudent() {
        return new Student(
                this.id,
                this.firstname,
                this.surname,
                LocalDate.parse(this.dob),
                this.sex,
                this.mail,
                this.phone,
                this.background,
                this.education,
                this.isPref,
                new Course(this.courseId),
                new House(this.houseId),
                new ArrayList<>()
        );
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

    public Boolean getPref() {
        return isPref;
    }

    public void setPref(Boolean pref) {
        isPref = pref;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public long getHouseId() {
        return houseId;
    }


    public int getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(int totalScores) {
        this.totalScores = totalScores;
    }
}
