package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class HouseServiceJpa implements HouseService {
    HouseRepositoryJpa houseRepo;

    public HouseServiceJpa(HouseRepositoryJpa houseRepo) {
        this.houseRepo = houseRepo;
    }

    @Override
    public List<HouseRanking> getRankings() {
        List<House> allHouses = houseRepo.findAll();
        List<HouseRanking> rankings = new ArrayList<>();
        for(House house : allHouses ){
            Integer houseScore = houseRepo.getTotalScoreByHouseId(house.getId());
            HouseRanking ranking = new HouseRanking(house, houseScore!=null?houseScore:0);
            rankings.add(ranking);
        }
        return rankings;
    }
}
