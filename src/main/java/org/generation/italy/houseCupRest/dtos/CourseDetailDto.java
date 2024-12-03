package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Course;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CourseDetailDto {
    private long id;
    private String className, startDate, endDate;
    private List<StudentDto> students;
    private List<TeacherDto> teachers;

    public CourseDetailDto(){}
    public CourseDetailDto(long id, String className, String startDate, String endDate, List<StudentDto> students,
                           List<TeacherDto> teachers) {
        this.id = id;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.students = students;
        this.teachers = teachers;
    }
    public CourseDetailDto(Course c){
        this.id = c.getId();
        this.className = c.getClassName();
        this.startDate = c.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate = c.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.students = c.getStudents().stream().map(StudentDto::new).toList();
        this.teachers = c.getTeachers().stream().map(TeacherDto::new).toList();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public List<StudentDto> getStudents() {
        return students;
    }
    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }
    public List<TeacherDto> getTeachers() {
        return teachers;
    }
    public void setTeachers(List<TeacherDto> teachers) {
        this.teachers = teachers;
    }
}
