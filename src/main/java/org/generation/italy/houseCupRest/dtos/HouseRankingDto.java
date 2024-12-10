package org.generation.italy.houseCupRest.dtos;
import org.generation.italy.houseCupRest.model.HouseRanking;
import java.util.Comparator;
import java.util.List;

public record HouseRankingDto(HouseDto[] rankings, HouseDto winner) {
    public HouseRankingDto (List<HouseRanking> rks){
        this(rks.isEmpty() ? new HouseDto[]{HouseDto.GRYFFINDOR_DEFAULT, HouseDto.HUFFLEPUFF_DEFAULT,
                        HouseDto.RAVENCLAW_DEFAULT, HouseDto.SLYTHERIN_DEFAULT} :
                        rks.stream().map(HouseDto::fromHouseRanking).toArray(HouseDto[]::new),
                rks.isEmpty() ? HouseDto.HUFFLEPUFF_DEFAULT :
                        rks.stream().max(Comparator.comparingInt(HouseRanking::getTotalScore))
                        .map(HouseDto::fromHouseRanking).get());
    }
}