package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;

public record HouseDto(long id, String name, int score) {
    public static HouseDto fromHouseRanking(HouseRanking hr){
        return new HouseDto(hr.getHouse().getId(), hr.getHouse().getHouseName(), hr.getTotalScore());
    }
}