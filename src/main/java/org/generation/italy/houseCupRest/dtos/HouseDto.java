package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;

public class HouseDto {

    private long id;
    private String name;
    private int score;
    private String houseImage;

    public HouseDto() {
    }

    public HouseDto(long id, String name, int score,String houseImage) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.houseImage = houseImage;
    }

    public HouseDto(House h) {
        this.id = h.getId();
        this.name = h.getHouseName();
        this.houseImage = h.getHouseImage();
    }

    public static HouseDto fromHouseRanking(HouseRanking hr) {
        return new HouseDto(
                hr.getHouse().getId(),
                hr.getHouse().getHouseName(),
                hr.getTotalScore(),
                hr.getHouse().getHouseImage()
        );
    }

    // Getter e Setter
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

    public String getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(String houseImage) {
        this.houseImage = houseImage;
    }
}
