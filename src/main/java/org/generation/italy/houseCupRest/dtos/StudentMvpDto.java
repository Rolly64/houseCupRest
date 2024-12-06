package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.StudentMvp;

public class StudentMvpDto {
    private String firstname, surname;
    public StudentMvpDto(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }
    public StudentMvpDto(StudentMvp student) {
        this.firstname = student.getFirstname();
        this.surname = student.getSurname();
    }
    public StudentMvpDto() {
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
}
