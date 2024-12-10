package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Student;

public class StudentDto {
    private long id;
    private String name;
    private String course;

    public StudentDto(){}
    public StudentDto(long id, String name,String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }
    public StudentDto(Student s){
        this.id = s.getId();
        this.name = s.getFirstname() +" "+ s.getSurname();
        this.course = s.getCourse().getClassName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
