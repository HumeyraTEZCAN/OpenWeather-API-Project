package com.humeyratezcan.bitirmeprojesi.cty.converter;

import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCityDto;
import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCitySaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CtyCityMapper {

    CtyCityMapper INSTANCE = Mappers.getMapper(CtyCityMapper.class);

    CtyCity convertToCity(CtyCitySaveRequestDto ctyCitySaveRequestDto);
    CtyCitySaveRequestDto convertToSaveRequestDto(CtyCity ctyCity);
    CtyCityDto convertToCityDto(CtyCity ctyCity);

}