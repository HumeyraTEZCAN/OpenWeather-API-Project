package com.humeyratezcan.bitirmeprojesi.air.service;

import com.humeyratezcan.bitirmeprojesi.air.converter.AirMapper;
import com.humeyratezcan.bitirmeprojesi.air.dto.AirDto;
import com.humeyratezcan.bitirmeprojesi.air.dto.AirSaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirService {

    private final AirEntityService airEntityService;
    private final AirMapper airMapper;
  public AirDto saveAir(AirSaveRequestDto airSaveRequestDto) {
        Air air = airMapper.convertToAir(airSaveRequestDto);

        air = airEntityService.save(air);
        AirDto airDto = airMapper.convertToAirDto(air);
        return airDto;
    }
    public List<Air> findAllAirs() {

        List<Air> airs = airEntityService.findAll();
        return airs;
    }

    public Air findByCity(CtyCity city) {

        Air air = airEntityService.findByCity(city);
        return air;
    }
    public Air updateAirPollution(Long airId, Double carbonMonoxide, Double ozone, Double sulphurDioxide, Date startDate, Date endDate)
    {
        Air air = airEntityService.findById(airId);

        air.setCarbonMonoxide(carbonMonoxide);
        air.setOzone(ozone);
        air.setSulphurDioxide(sulphurDioxide);
        air.setStartDate(startDate);
        air.setEndDate(endDate);
        air = airEntityService.save(air);
        return air;
    }



}
