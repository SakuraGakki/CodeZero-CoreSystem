package com.codezero.cms.entity.information;

import lombok.Data;

@Data
public class Weather {
    private String country;
    private String province;
    private String city;
    private String weather;
    private String date;
    private String temperature;
    private String humidity;
    private String direct;
    private String power;
    private String aqi;
}
