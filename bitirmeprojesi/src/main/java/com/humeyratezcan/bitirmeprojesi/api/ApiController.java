package com.humeyratezcan.bitirmeprojesi.api;
import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.air.service.AirService;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.cty.service.CtyCityService;
import org.json.JSONArray;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/api-controller")
public class ApiController {

    private final CtyCityService cityService;

    private final AirService airService;

    private final ApiService apiService;


    @PatchMapping("saveCityCoordinates")
    public ResponseEntity<CtyCity> saveCityCoordinates(@RequestParam String cityName){

        String url = "http://api.openweathermap.org/geo/1.0/direct?q="+cityName+"&limit=1&appid=8a997ddd116acc93f07afc022be12aa6";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

       String body = responseEntity.getBody();
        int i = body.indexOf("{");
        body = body.substring(i); /**/
        CtyCity city = cityService.findByCityName(cityName);
        JSONObject json = new JSONObject(body);
        Double longitude = Double.valueOf(json.getDouble("lon"));
        Double latitude =  Double.valueOf(json.getDouble("lat"));

        city = cityService.updateCityCoordinates(city.getId(),cityName,longitude,latitude);

        return ResponseEntity.ok(city);
    }

    @PatchMapping("saveAirPollution")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public ResponseEntity<Air> saveAirPollution(@RequestParam(required = true) String cityName, @RequestParam(required = false) Date startDate,@RequestParam(required = false)  Date endDate) throws ParseException {

        CtyCity city = cityService.findByCityName(cityName);
        Long start, end;
        String oldestDate="2020/11/27";
        Date lastdate =new SimpleDateFormat("yyyy/MM/dd").parse(oldestDate);

        if(startDate == null | endDate == null){
            Date today = new Date();
            long lastWeek = System.currentTimeMillis() - (86400 * 7 * 1000);
            Date last = new Date(lastWeek);
            end = today.getTime()/1000L;
            start = last.getTime()/1000L;


        }
        else if(startDate.getTime() < lastdate.getTime() | endDate.getTime() < lastdate.getTime()){
            throw  new RuntimeException("There is no available info for this date !!!");
        }
        else{
            start = startDate.getTime()/1000L;
            end =endDate.getTime()/1000L;
        }


        String url = "http://api.openweathermap.org/data/2.5/air_pollution/history?lat="+city.getCityLatitude().toString()+"8&lon="+city.getCityLongitude().toString()+"&start="+start.toString()+"&end="+end.toString()+"&appid=8a997ddd116acc93f07afc022be12aa6";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String body = responseEntity.getBody();
        int j = body.indexOf("{");
        body = body.substring(j);
        Air air = airService.findByCity(city);
        JSONObject json = new JSONObject(body);
        JSONArray airValues = (JSONArray) json.get("list");
        Double avgCO = Double.valueOf(0);
        Double avgSO2 = Double.valueOf(0);
        Double avgO3 = Double.valueOf(0);
        for (int i = 0; i < airValues.length(); ++i) {
            JSONObject rec = airValues.getJSONObject(i);
            Double dt = rec.getDouble("dt");
            JSONObject components = rec.getJSONObject("components");
            Double co = components.getDouble("co");
            Double o3 = components.getDouble("o3");
            Double so2 = components.getDouble("so2");
            avgCO += co;
            avgO3 += o3;
            avgSO2 += so2;
        }
        avgCO = avgCO/airValues.length();
        avgO3 = avgCO/airValues.length();
        avgSO2 = avgCO/airValues.length();
        air = airService.updateAirPollution(air.getId(), avgCO, avgO3, avgSO2 , startDate, endDate);

        return ResponseEntity.ok(air);
    }
    @GetMapping
    public String getResults(){
      String myResult = apiService.showJsonResults();
      return myResult;

    }

}