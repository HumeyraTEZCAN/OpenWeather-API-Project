package com.humeyratezcan.bitirmeprojesi.air.dao;


import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface AirDao extends JpaRepositoryImplementation<Air,Long> {
    Air findByCarbonMonoxide(Double carbonMonoxide);
    Air findByCity(CtyCity city);

}
