package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Teacher;

public class TeacherDto {
    private long id;
    private String firstname;
    private String surname;

    public TeacherDto(){}
    public TeacherDto(long id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
    }
    public TeacherDto(Teacher t){
        this.id = t.getId();
        this.firstname = t.getFirstname();
        this.surname = t.getSurname();
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
}
