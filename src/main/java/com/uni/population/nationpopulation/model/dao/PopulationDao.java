package com.uni.population.nationpopulation.model.dao;

import com.uni.population.nationpopulation.model.dto.PopulationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PopulationDao {

    List<PopulationDto> findAllNation();

    int registNation(PopulationDto newPopulation);

    PopulationDto findNationByCode(int nationno);

    void deletePopulation(int nationno);

    int updatePopulation(PopulationDto populationDto);


}
