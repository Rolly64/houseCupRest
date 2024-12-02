package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;
import org.generation.italy.houseCupRest.model.repositories.HouseRepositoryJpa;

import java.util.ArrayList;
import java.util.List;

public class HouseServiceJpa implements HouseService{
    HouseRepositoryJpa houseRepo;
    public HouseServiceJpa(HouseRepositoryJpa houseRepo){
        this.houseRepo = houseRepo;
    }
    @Override
    public List<HouseRanking> getRankings() {
        List<House> allHouses = houseRepo.findAll();
        List<HouseRanking> rankings = new ArrayList<>();
        for(House h : allHouses){
            int houseScore = houseRepo.getTotalScoreByHouseId(h.getId());
            HouseRanking ranking = new HouseRanking(h,houseScore);
            rankings.add(ranking);
        }
        return List.of();
    }
}
