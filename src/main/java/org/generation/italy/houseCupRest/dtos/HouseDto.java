package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;

public record HouseDto(long id, String name, int score) {
//    public HouseDto(long id, String name){
//        this(id, name, 0);
//    }
    public static HouseDto fromHouse(House h){
        return new HouseDto(h.getId(), h.getHouseName(), 0);
    }
    public static HouseDto fromHouseRanking(HouseRanking hr){
        return new HouseDto(hr.getHouse().getId(), hr.getHouse().getHouseName(), hr.getTotalScore());
    }
    public static HouseDto GRYFFINDOR_DEFAULT = new HouseDto(0, "GRYFFINDOR", 0);
    public static HouseDto HUFFLEPUFF_DEFAULT = new HouseDto(0, "HUFFLEPUFF", 0);
    public static HouseDto RAVENCLAW_DEFAULT = new HouseDto(0, "RAVENCLAW", 0);
    public static HouseDto SLYTHERIN_DEFAULT = new HouseDto(0, "SLYTHERIN", 0);
}