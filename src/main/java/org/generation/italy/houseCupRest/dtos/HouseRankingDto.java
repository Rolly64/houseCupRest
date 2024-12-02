package org.generation.italy.houseCupRest.dtos;

import org.generation.italy.houseCupRest.model.HouseRanking;

import java.util.List;

public record HouseRankingDto(HouseDto[] rankings, HouseDto winner) {
//    public HouseRankingDto(List<HouseRanking> rks){
//        var newRankings = new HouseDto[rks.size()];
//        for(int i = 0;i<rks.size();i++) {
//            HouseRanking rank = rks.get(i);
//            HouseDto houseDto = new HouseDto(rank.getHouse().getId(), rank.getHouse().getHouseName(), rank.getTotalScore());
//            newRankings[i] = houseDto;
//        }
//        this.winner = null;
//        int maxScore = Integer.MIN_VALUE;
//        HouseDto max = null;
//        for(int j = 0;j<newRankings.length;j++){
//            if(newRankings[j].score()>maxScore){
//                maxScore = newRankings[j].score();
//                max = newRankings[j];
//            }
//        }
////        this(rks.stream().map(HouseDto::fromHouseRanking().toArray(),null);
//    }
}
