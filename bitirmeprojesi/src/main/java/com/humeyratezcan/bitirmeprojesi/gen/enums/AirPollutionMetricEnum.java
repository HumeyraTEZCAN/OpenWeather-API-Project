package com.humeyratezcan.bitirmeprojesi.gen.enums;
import java.util.EnumMap;

public class AirPollutionMetricEnum {

        public enum airQuality {
            GOOD ,
            SATISFACTORY,
            MODERATE,
            POOR,
            SEVERE,
            HAZARDOUS;
        }
        public static EnumMap<airQuality, Integer> airMap;

    public static void main(String args[])
        {


            airMap = new EnumMap<airQuality, Integer>(airQuality.class);

            airMap.put(airQuality.GOOD, 50);
            airMap.put(airQuality.SATISFACTORY, 100);
            airMap.put(airQuality.MODERATE, 200);
            airMap.put(airQuality.POOR, 300);
            airMap.put(airQuality.SEVERE, 400);
            airMap.put(airQuality.HAZARDOUS, 401);


        }


}
