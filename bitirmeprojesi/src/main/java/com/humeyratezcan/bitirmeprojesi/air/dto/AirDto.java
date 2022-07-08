package com.humeyratezcan.bitirmeprojesi.air.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

@Data
@Getter
@Setter
public class AirDto {
    private Long airId;
    private Double carbonMonoxide;
    private Double ozone;
    private Double sulphurDioxide;
    private Date startDate;
    private Date endDate;
    private Long cityId;

}
