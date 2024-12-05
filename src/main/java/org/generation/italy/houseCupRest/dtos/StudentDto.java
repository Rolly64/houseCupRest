package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Student;

import java.util.List;

public class StudentDto {
    private long id;
    private String firstname, surname;

    public StudentDto(){}
    public StudentDto(long id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
    }
    public StudentDto(Student s){
        this.id = s.getId();
        this.firstname = s.getFirstname();
        this.surname = s.getSurname();
    }
    public static List<StudentDto> fromStudents(List<Student> students){
        return students.stream().map(StudentDto::new).toList();
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
