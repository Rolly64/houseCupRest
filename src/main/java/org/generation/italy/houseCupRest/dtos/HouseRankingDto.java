package org.generation.italy.houseCupRest.dtos;
import org.generation.italy.houseCupRest.model.HouseRanking;
import java.util.Comparator;
import java.util.List;

public record HouseRankingDto(HouseDto[] rankings, HouseDto winner) {
    public HouseRankingDto (List<HouseRanking> rks){
//        var newRankings = new HouseDto[rks.size()];
//        for(int i=0; i< rks.size(); i++) {
//            HouseRanking rank = rks.get(i);
//            HouseDto houseDto = new HouseDto(rank.getHouse().getId(), rank.getHouse().getHouseName(), rank.getTotalScore());
//            newRankings[i] = houseDto;
//        }
//        int maxScore = Integer.MIN_VALUE;
//        HouseDto max = null;
//        for(int z=0; z<newRankings.length; z++){
//            if(newRankings[z].score()>maxScore){
//                maxScore=newRankings[z].score();
//                max = newRankings[z];
//            }
//        }
        this(rks.stream().map(HouseDto::fromHouseRanking).toArray(HouseDto[]::new),
                rks.stream().max(Comparator.comparingInt(HouseRanking::getTotalScore))
                        .map(HouseDto::fromHouseRanking).get());
    }
}
