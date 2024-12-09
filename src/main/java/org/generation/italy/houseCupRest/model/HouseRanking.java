package org.generation.italy.houseCupRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.generation.italy.houseCupRest.model.entities.House;

public class HouseRanking {
    private House house;
    private int totalScore;

    public HouseRanking(House house, int totalScore) {
        this.house = house;
        this.totalScore = totalScore;
    }
    @JsonIgnore
    public House getHouse() {
        return house;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
