package org.generation.italy.brewverse.model.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "brewery_id")
    private Brewery brewery;
    private String type;
    @Column(name = "alcohol_percentage")
    private double alcoholPercentage;
    @Column(name = "rating_avarage")
    private double avarageRating;
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "beer")
    private List<Review> reviews;

    public Beer() {
    }

    public Beer(long id, String name, Brewery brewery, String type, double alcoholPercentage, double avarageRating, String imageUrl, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.brewery = brewery;
        this.type = type;
        this.alcoholPercentage = alcoholPercentage;
        this.avarageRating = avarageRating;
        this.imageUrl = imageUrl;
        this.reviews = reviews;
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

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public double getAvarageRating() {
        return avarageRating;
    }

    public void setAvarageRating(double avarageRating) {
        this.avarageRating = avarageRating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
