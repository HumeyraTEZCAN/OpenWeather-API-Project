package com.humeyratezcan.bitirmeprojesi.cty.dao;

import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCityDto;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Component;

public interface CtyCityDao extends JpaRepositoryImplementation<CtyCity,Long> {
    CtyCity findByCityName(String cityName);
}
