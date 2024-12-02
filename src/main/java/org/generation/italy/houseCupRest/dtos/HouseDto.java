package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;

import java.util.List;

public record HouseDto(long id, String name, int score) {
//    public static fromHouseRanking(HouseRanking hr){
//        return new HouseDto(hr.getHouse().getId(),hr.getHouse().getHouseName(),hr.getTotalScore());
//    }
}
