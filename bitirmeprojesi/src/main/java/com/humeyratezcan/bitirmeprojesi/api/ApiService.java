package com.humeyratezcan.bitirmeprojesi.api;

import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.air.service.AirService;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.cty.service.CtyCityService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService {

   private final CtyCityService ctyCityService;
    private final AirService airService;
    public String showJsonResults(){

        String message;
        JSONObject json = new JSONObject();

        List<CtyCity> cities = ctyCityService.findAllCities();
        List<Air> airs = airService.findAllAirs();

        JSONArray resultsarray = new JSONArray();
        JSONArray categoriesarray = new JSONArray();
        JSONObject resultitem = new JSONObject();
        JSONObject categoryitem = new JSONObject();

        for(int i =0; i<cities.size();i++){
            json.put("City", cities.get(i).getCityName());
            resultitem.put("Date", airs.get(i).getStartDate());
            resultitem.put("Categories", categoriesarray);
            categoryitem.put("CO", airs.get(i).getCarbonMonoxide());
            categoryitem.put("SO2", airs.get(i).getSulphurDioxide());
            categoryitem.put("O3", airs.get(i).getOzone());


        }

        resultsarray.put(resultitem);
        categoriesarray.put(categoryitem);
        resultsarray.put(categoriesarray);
        json.put("Results", resultsarray);

        message = json.toString();

        // message
        // {"course":[{"id":3,"information":"test","name":"course1"}],"name":"student"}
        return message;
    }






}
