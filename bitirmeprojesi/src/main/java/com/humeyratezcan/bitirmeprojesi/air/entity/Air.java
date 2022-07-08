package com.humeyratezcan.bitirmeprojesi.air.entity;


import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.gen.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;

@EntityScan
@Entity
@Getter
@Setter
@Data
@Table(name = "AIR")
public class Air extends BaseEntity {
    @Id
    @SequenceGenerator(name = "Air", sequenceName = "AIRAIR_ID_SEQ")
    @GeneratedValue(generator = "Air")
    private Long id;

    @Column(name = "AIR_CARBON_MONOXIDE")
    private Double carbonMonoxide;

    @Column(name = "AIR_OZONE")
    private Double ozone;

    @Column(name = "VEHICLE_SULPHUR_DIOXIDE")
    private Double sulphurDioxide;


    @Column(name = "START_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "END_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CTY_CITY")
    private CtyCity city;




}
