package com.uni.population.nationpopulation.model.service;

import com.uni.population.nationpopulation.model.dao.PopulationDao;
import com.uni.population.nationpopulation.model.dto.PopulationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PopulationService {

    private final PopulationDao populationDao;

    @Autowired
    public PopulationService(PopulationDao populationDao){
        this.populationDao = populationDao;
    }

    public List<PopulationDto> findAllNation() {
        return populationDao.findAllNation();
    }



    public PopulationDto findNationByCode(int nationno) {
        return populationDao.findNationByCode(nationno);
    }

    public ResponseEntity<String> deletePopulation(int nationno) {
        populationDao.deletePopulation(nationno);
        return ResponseEntity.ok("Population deleted successfully");
    }
}
