package com.humeyratezcan.bitirmeprojesi.cty.entity;


import com.humeyratezcan.bitirmeprojesi.gen.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "CTY_CITY")
public class CtyCity extends BaseEntity {

    @Id
    @SequenceGenerator(name ="CtyCity", sequenceName = "CTY_CITY_ID_SEQ")
    @GeneratedValue(generator = "CtyCity")
    private Long id;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "CITY_LAT")
    private Double cityLatitude;

    @Column(name = "CITY_LON")
    private Double cityLongitude;



}

