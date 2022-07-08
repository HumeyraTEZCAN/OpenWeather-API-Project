package com.humeyratezcan.bitirmeprojesi.cty.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CtyCityDto {
    private Long id;
    private String cityName;
    private String cityLatitude;
    private String cityLongitude;

}
