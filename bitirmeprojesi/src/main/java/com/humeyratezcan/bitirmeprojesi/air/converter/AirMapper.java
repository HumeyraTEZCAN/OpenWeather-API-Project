package com.humeyratezcan.bitirmeprojesi.air.converter;

import com.humeyratezcan.bitirmeprojesi.air.dto.AirDto;
import com.humeyratezcan.bitirmeprojesi.air.dto.AirSaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.air.service.AirEntityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class AirMapper {

    @Autowired
    AirEntityService airEntityService;
    @Mapping(target = "cityId",source = "city.id")
    public abstract AirDto convertToAirDto(Air air);

    @Mapping(target = "city.id",source = "cityId")
    public abstract Air convertToAir(AirSaveRequestDto airSaveRequestDto);
}
