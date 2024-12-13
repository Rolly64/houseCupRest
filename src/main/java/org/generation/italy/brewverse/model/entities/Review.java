package org.generation.italy.brewverse.model.entities;

import jakarta.persistence.*;
import org.generation.italy.brewverse.authentication.entity.User;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double rating;
    private String description;
    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private User member;

    public Review() {
    }

    public Review(double rating, String description, Beer beer, User member) {
        this.rating = rating;
        this.description = description;
        this.beer = beer;
        this.member = member;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }
}
