package com.humeyratezcan.bitirmeprojesi.air.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class AirSaveRequestDto {
    private Double carbonMonoxide;
    private Double ozone;
    private Double sulphurDioxide;
    private Date startDate;
    private Date endDate;
    private Long cityId;
}
