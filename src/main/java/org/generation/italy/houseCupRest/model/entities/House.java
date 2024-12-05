package org.generation.italy.houseCupRest.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private long id;
    @Column(name = "house_name")
    private String houseName;
    @Column(name = "house_img")
    private String houseImage;
    @OneToMany(mappedBy = "house")
    private List<Student> students = new ArrayList<>();

    public House(){

    }

    public House(long id, String houseName, String houseImage) {
        this.id = id;
        this.houseName = houseName;
        this.houseImage = houseImage;
    }

    public long getId() {
        return id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}