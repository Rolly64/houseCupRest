package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.entities.Course;

import java.time.format.DateTimeFormatter;

public class CourseDto {
    private long id;
    private String className, startDate, endDate;

    public CourseDto(){     }
    public CourseDto(long id, String className, String startDate, String endDate) {
        this.id = id;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public CourseDto(Course c){
        this.id = c.getId();
        this.className = c.getClassName();
        this.startDate = c.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.endDate = c.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
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
}
