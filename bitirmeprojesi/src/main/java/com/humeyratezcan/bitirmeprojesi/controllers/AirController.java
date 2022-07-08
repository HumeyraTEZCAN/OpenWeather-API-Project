package com.humeyratezcan.bitirmeprojesi.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.humeyratezcan.bitirmeprojesi.air.dto.AirSaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.air.service.AirService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLEngineResult;
import java.util.List;

@RestController
@RequestMapping("/api/v1/airvalues")
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AirController {

    private final AirService airService;

    @PostMapping("/saveNewAir")
    public ResponseEntity saveNewAir(@RequestBody AirSaveRequestDto airSaveRequestDto){
        airService.saveAir(airSaveRequestDto);
        return new ResponseEntity(airSaveRequestDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAirs")
    public ResponseEntity<List<Air>> findAllAirs(){

        List<Air> airs = airService.findAllAirs();
        System.out.println("airss");
        System.out.println(airs);
        return new ResponseEntity<>(airs, HttpStatus.OK);
    }
}
