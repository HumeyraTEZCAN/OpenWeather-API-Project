package com.humeyratezcan.bitirmeprojesi.cty.service;

import com.humeyratezcan.bitirmeprojesi.cty.dao.CtyCityDao;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.gen.service.BaseEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class CtyCityEntityService extends BaseEntityService<CtyCity, CtyCityDao> {

    @Autowired
    public CtyCityEntityService(CtyCityDao ctyCityDao) {
        super(ctyCityDao);
    }

   public CtyCity findByCityName(String cityName){return getDao().findByCityName(cityName);}



}