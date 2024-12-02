package org.generation.italy.houseCupRest.model;

import org.generation.italy.houseCupRest.model.entities.House;

public class HouseRanking {
    private House house;
    private int totalScore;

    public HouseRanking(House house, int totalScore) {
        this.house = house;
        this.totalScore = totalScore;
    }

    public House getHouse() {
        return house;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
