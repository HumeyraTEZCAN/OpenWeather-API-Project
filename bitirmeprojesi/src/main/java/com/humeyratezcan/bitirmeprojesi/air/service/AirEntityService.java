package com.humeyratezcan.bitirmeprojesi.air.service;

import com.humeyratezcan.bitirmeprojesi.air.dao.AirDao;
import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.gen.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AirEntityService extends BaseEntityService<Air, AirDao> {
    @Autowired
    public AirEntityService(AirDao airDao) {
        super(airDao);
    }
    public Air findByCarbonMonoxide(Double carbonMonoxide){return getDao().findByCarbonMonoxide(carbonMonoxide);}

    public Air findByCity(CtyCity city){return getDao().findByCity(city);}



}
