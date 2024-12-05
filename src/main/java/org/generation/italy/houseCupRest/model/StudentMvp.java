package org.generation.italy.houseCupRest.model;

public class StudentMvp {
    private String firstname, surname;

    public StudentMvp(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    public StudentMvp() {
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
