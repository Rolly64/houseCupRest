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
}