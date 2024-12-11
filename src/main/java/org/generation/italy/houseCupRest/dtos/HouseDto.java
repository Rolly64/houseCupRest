package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;

public class HouseDto {
    private long id;
    private String name;
    private int score;

    public HouseDto(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    public HouseDto(House house){
        this.id = house.getId();
        this.name = house.getHouseName();
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static HouseDto fromHouseRanking(HouseRanking hr) {
        return new HouseDto(
                hr.getHouse().getId(),
                hr.getHouse().getHouseName(),
                hr.getTotalScore()
        );
    }

    @Override
    public String toString() {
        return "HouseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

