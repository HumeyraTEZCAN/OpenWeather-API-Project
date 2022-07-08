package com.humeyratezcan.bitirmeprojesi.controllers;


import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCitySaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.cty.service.CtyCityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cities")
public class CtyCityController {

    private final CtyCityService ctyCityService;

    @PostMapping("/newCity")
    public ResponseEntity saveNewCity(@RequestBody CtyCitySaveRequestDto ctyCitySaveRequestDto) {
        ctyCityService.saveCity(ctyCitySaveRequestDto);
        return ResponseEntity.ok(ctyCitySaveRequestDto);
    }
    @GetMapping("/getAllCities")
    public ResponseEntity findAllCities(){

        List<CtyCity> cities = ctyCityService.findAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }


}
