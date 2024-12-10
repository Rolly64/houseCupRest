package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDetailDto {
    private long id;
    private String firstname;
    private String surname;
    private String dob;
    private char sex;
    private String email;
    private String phone;
    private boolean isPref;
    private String background;
    private String education;
    private long classId;
    private long houseId;
    private String className;
    private String houseName;

    public static StudentDetailDto fromStudent(Student s){
        return new StudentDetailDto(s.getId(), s.getFirstname(), s.getSurname(),
                s.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE), s.getSex(), s.getMail(), s.getPhone(), s.getIsPref(),
                s.getBackground(), s.getEducation(), s.getCourse().getId(), s.getHouse().getId(),
                s.getCourse().getClassName(), s.getHouse().getHouseName());
    }

    public Student toStudent(){
        return new Student(this.id, this.firstname, this.surname, LocalDate.parse(this.dob), this.sex, this.email,
                this.phone, this.background, this.education, this.isPref, null, null, null);
    }

    public StudentDetailDto(){}

    public StudentDetailDto(long id, String firstname, String surname, String dob, char sex, String email, String phone,
                            boolean isPref, String background, String education, long classId, long houseId,
                            String className, String houseName) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.isPref = isPref;
        this.background = background;
        this.education = education;
        this.classId = classId;
        this.houseId = houseId;
        this.className = className;
        this.houseName = houseName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPref() {
        return isPref;
    }

    public void setPref(boolean pref) {
        isPref = pref;
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

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
