package com.humeyratezcan.bitirmeprojesi.cty.service;

import com.humeyratezcan.bitirmeprojesi.cty.converter.CtyCityMapper;
import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCityDto;
import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCitySaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CtyCityService {

    private final CtyCityEntityService ctyCityEntityService;


    public CtyCityDto saveCityDto(CtyCitySaveRequestDto ctyCitySaveRequestDto) {
        CtyCity city = CtyCityMapper.INSTANCE.convertToCity(ctyCitySaveRequestDto);

        if(ctyCityEntityService.findByCityName(city.getCityName())!=null)
        {
            throw new RuntimeException("this city has already added!");
        }

        city = ctyCityEntityService.save(city);
        CtyCityDto ctyCityDto = CtyCityMapper.INSTANCE.convertToCityDto(city);
        return ctyCityDto;
    }

    public CtyCity saveCity(CtyCitySaveRequestDto ctyCitySaveRequestDto) {
        CtyCity city = CtyCityMapper.INSTANCE.convertToCity(ctyCitySaveRequestDto);

        city = ctyCityEntityService.save(city);
        return city;
    }
    public List<CtyCity> findAllCities() {

        List<CtyCity> cities = ctyCityEntityService.findAll();
        return cities;
    }


    public CtyCity findByCityName(String cityName) {

        CtyCity city = ctyCityEntityService.findByCityName(cityName);
        return city;
    }
    public CtyCity updateCityCoordinates(Long cityId, String cityName, Double longitude, Double latitude)
    {
        CtyCity city = ctyCityEntityService.findById(cityId);
        city.setCityLatitude(latitude);
        city.setCityName(cityName);
        city.setCityLongitude(longitude);
        city = ctyCityEntityService.save(city);
        return city;
    }



}
