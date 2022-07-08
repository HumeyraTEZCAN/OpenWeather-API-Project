package com.humeyratezcan.bitirmeprojesi.api;
import com.humeyratezcan.bitirmeprojesi.air.entity.Air;
import com.humeyratezcan.bitirmeprojesi.air.service.AirService;
import com.humeyratezcan.bitirmeprojesi.cty.converter.CtyCityMapper;
import com.humeyratezcan.bitirmeprojesi.cty.dto.CtyCitySaveRequestDto;
import com.humeyratezcan.bitirmeprojesi.cty.entity.CtyCity;
import com.humeyratezcan.bitirmeprojesi.cty.service.CtyCityService;
import org.json.JSONArray;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/api-controller")
public class ApiController {

    private final CtyCityService cityService;

    private final AirService airService;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String saveAirPollution(@RequestParam String cityName, Date startDate, Date endDate){

        CtyCity city = cityService.findByCityName(cityName);

        Long start = startDate.getTime()/1000L;
        Long end =endDate.getTime()/1000L;

        String url = "http://api.openweathermap.org/data/2.5/air_pollution/history?lat="+city.getCityLatitude().toString()+"8&lon="+city.getCityLongitude().toString()+"&start="+start.toString()+"&end="+end.toString()+"&appid=8a997ddd116acc93f07afc022be12aa6";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        String body = responseEntity.getBody();
        int j = body.indexOf("{");
        body = body.substring(j);
        Air air = airService.findByCity(city);
        JSONObject json = new JSONObject(body);
        //Double longitude = Double.valueOf(json.getDouble("lon"));
        //Double latitude =  Double.valueOf(json.getDouble("lat"));
        JSONArray airValues = (JSONArray) json.get("list");
         //JSONObject components = airValues.
         //ystem.out.println(components);
        Double avgCO = Double.valueOf(0);
        Double avgSO2 = Double.valueOf(0);
        Double avgO3 = Double.valueOf(0);
        for (int i = 0; i < airValues.length(); ++i) {
            JSONObject rec = airValues.getJSONObject(i);
            Double dt = rec.getDouble("dt");
            System.out.println("dt burada:");
            System.out.println(dt);
            JSONObject components = rec.getJSONObject("components");
            System.out.println(components);
            Double co = components.getDouble("co");
            System.out.println(co);
            Double o3 = components.getDouble("o3");
            Double so2 = components.getDouble("so2");
            avgCO += co;
            avgO3 += o3;
            avgSO2 += so2;
            //String loc = rec.getString("loc");
            // ...
        }
        avgCO = avgCO/airValues.length();
        avgO3 = avgCO/airValues.length();
        avgSO2 = avgCO/airValues.length();
        air = airService.updateAirPollution(air.getId(), avgCO, avgO3, avgSO2 , startDate, endDate);

        return body;
    }






/*
    @GetMapping("/mail-send-request-dto")
    public MailSendRequestDto getMailSendRequestDto(){

        String url = "http://localhost:8081/api/v1/mail-sender/mail-send-request-dto";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MailSendRequestDto> responseEntity = restTemplate.getForEntity(url, MailSendRequestDto.class);

        return responseEntity.getBody();
    }

    @PostMapping("/mail-sender")
    public boolean sendMail(@RequestBody MailSendRequestDto mailSendRequestDto){

        String url = "http://localhost:8081/api/v1/mail-sender";

        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

        HttpEntity<MailSendRequestDto> request = new HttpEntity<>(mailSendRequestDto);

        ResponseEntity<Boolean> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, Boolean.class);
        } catch (Exception e){
            throw new RuntimeException("Error!");
        }
//        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url, mailSendRequestDto, Boolean.class);

        Boolean isSuccess = responseEntity.getBody();

        return isSuccess;
    }

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory()
    {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        //Connect timeout
        clientHttpRequestFactory.setConnectTimeout(5_000);

        //Read timeout
        clientHttpRequestFactory.setReadTimeout(5_000);
        return clientHttpRequestFactory;
    }*/
}