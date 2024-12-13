package org.generation.italy.brewverse.model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "breweries")
public class Brewery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brewery_id")
    private long id;
    private String name;
    @Column(name = "established_year")
    private long establishedYear;
    private String website;
    private String location;
    private String description;
    @OneToMany
    @JoinColumn(name = "brewery_id")
    private List<Beer> beers = new ArrayList<>();

    public Brewery() {
    }

    public Brewery(long id, String name, long establishedYear, String website, String location, String description, List<Beer> beers) {
        this.id = id;
        this.name = name;
        this.establishedYear = establishedYear;
        this.website = website;
        this.location = location;
        this.description = description;
        this.beers = beers;
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

    public long getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(long establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
