package org.generation.italy.houseCupRest.dtos;
import org.generation.italy.houseCupRest.model.HouseRanking;
import java.util.Comparator;
import java.util.List;

public record HouseRankingDto(HouseDto[] rankings, HouseDto winner) {
    public HouseRankingDto (List<HouseRanking> rks){
        this(rks.stream().map(HouseDto::fromHouseRanking).toArray(HouseDto[]::new),
                rks.stream().max(Comparator.comparingInt(HouseRanking::getTotalScore))
                        .map(HouseDto::fromHouseRanking).get());
    }
}
