package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;

public class HouseDto {
    //(long id, String name, int score)
    private long id;
    private String name;
    private int score;

    public HouseDto() {}
    public HouseDto(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public HouseDto(House h) {
        this.id = h.getId();
        this.name = h.getHouseName();
    }
    public static HouseDto fromHouseRanking(HouseRanking hr){
        return new HouseDto(hr.getHouse().getId(), hr.getHouse().getHouseName(), hr.getTotalScore());
    }
}
