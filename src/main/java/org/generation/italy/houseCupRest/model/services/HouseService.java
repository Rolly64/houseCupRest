package org.generation.italy.houseCupRest.model.services;

import org.generation.italy.houseCupRest.dtos.HouseRankingDto;
import org.generation.italy.houseCupRest.model.HouseRanking;
import org.generation.italy.houseCupRest.model.entities.House;

import java.util.List;

public interface HouseService {
   List<HouseRanking> getRankings();
   List<House> getAllHouses();
}
