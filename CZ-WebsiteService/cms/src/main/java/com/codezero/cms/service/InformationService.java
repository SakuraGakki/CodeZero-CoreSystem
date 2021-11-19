package com.codezero.cms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codezero.cms.entity.base.BaseResponse;
import com.codezero.cms.entity.information.*;
import com.codezero.cms.utils.DateUtils;
import com.codezero.cms.utils.RedisUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class InformationService {

    @Resource
    private RedisUtil redisUtil;

    public BaseResponse<List<News>> getNewsInfo(){
        BaseResponse<List<News>> baseResponse = new BaseResponse<>();
        List<News> newsList = new ArrayList<>();
        JSONArray listObj = JSONArray.parseArray(redisUtil.get("newsInfo").toString());

        for(Object obj:listObj){
            News news =  new News();
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
            news = JSONObject.toJavaObject(jsonObject, News.class);
            newsList.add(news);
        }
        baseResponse.setData(newsList);
        return baseResponse;
    }

    public BaseResponse<List<Keywords>> getKeywordsInfo(){
        BaseResponse<List<Keywords>> baseResponse = new BaseResponse<>();
        List<Keywords> keywordsList = new ArrayList<>();
        JSONArray listObj = JSONArray.parseArray(redisUtil.get("keywordsInfo").toString());

        for(Object obj:listObj){
            Keywords keywords =  new Keywords();
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
            keywords = JSONObject.toJavaObject(jsonObject, Keywords.class);
            keywordsList.add(keywords);
        }
        baseResponse.setData(keywordsList);
        return baseResponse;
    }

    public BaseResponse<Days> getDaysInfo(){
        BaseResponse<Days> baseResponse = new BaseResponse<>();
        Object obj = JSONObject.parseObject(redisUtil.get("calendarInfo").toString());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
        Days days = JSONObject.toJavaObject(jsonObject,Days.class);
        baseResponse.setData(days);
        return baseResponse;
    }

    public BaseResponse<Weather> getLocationAndWeather(Location location){
        BaseResponse<Weather> baseResponse = new BaseResponse<>();
        Weather weather = new Weather();
        Object localObj = getLocationInfo(location);
        JSONObject localJson = (JSONObject) JSONObject.toJSON(localObj);
        String cityInfo = localJson.getString("display_name");
        String country = cityInfo.split(",")[cityInfo.split(",").length-1];
        String province = cityInfo.split(",")[cityInfo.split(",").length-3];
        String city = cityInfo.split(",")[cityInfo.split(",").length-4];
        weather.setCountry(country);
        weather.setProvince(province);
        weather.setCity(city);
        //先得到城市信息然后去redis中查询当天天气信息，如果没有再调用接口
        if(redisUtil.hasKey(DateUtils.getDateByString()+city)){
            Object obj = JSONObject.parseObject(redisUtil.get(DateUtils.getDateByString()+city).toString());
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(obj);
            weather.setDate(jsonObject.getString("date"));
            weather.setWeather(jsonObject.getString("weather"));
            weather.setTemperature(jsonObject.getString("temperature"));
            weather.setHumidity(jsonObject.getString("humidity"));
            weather.setDirect(jsonObject.getString("direct"));
            weather.setPower(jsonObject.getString("power"));
            weather.setAqi(jsonObject.getString("aqi"));
        }else{
            Object weatherObj = getWeatherInfo(city);
            JSONObject weatherJson = (JSONObject) JSONObject.toJSON(weatherObj);
            if("查询成功".equals(weatherJson.getString("msg"))){
                weather.setDate(weatherJson.getJSONObject("result").getJSONObject("result").getString("date"));
                weather.setWeather(weatherJson.getJSONObject("result").getJSONObject("result").getString("weather"));
                weather.setTemperature(weatherJson.getJSONObject("result").getJSONObject("result").getString("templow")+"~"+weatherJson.getJSONObject("result").getJSONObject("result").getString("temphigh"));
                weather.setHumidity(weatherJson.getJSONObject("result").getJSONObject("result").getString("humidity"));
                weather.setDirect(weatherJson.getJSONObject("result").getJSONObject("result").getString("winddirect"));
                weather.setPower(weatherJson.getJSONObject("result").getJSONObject("result").getString("windpower"));
                weather.setAqi(weatherJson.getJSONObject("result").getJSONObject("result").getJSONObject("aqi").getString("aqi"));
                redisUtil.set(DateUtils.getDateByString()+city,JSONObject.toJSONString(weather));
            }else{
                baseResponse.setStatus(-1);
                baseResponse.setMessage("天气查询失败！");
                return baseResponse;
            }
        }
        baseResponse.setData(weather);
        return baseResponse;
    }

    protected Object getLocationInfo(Location location){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://nominatim.openstreetmap.org/reverse";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("format", "json");
        params.add("lat", location.getLatitude());
        params.add("lon", location.getLongitude());
        params.add("zom","18");
        params.add("addressdetails","1");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<Object> forEntity = restTemplate.getForEntity(uri, Object.class);
        return forEntity.getBody();
    }

    protected Object getWeatherInfo(String city){
        if(city.contains("市")){
            city = city.substring(0,city.length()-1);
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://way.jd.com/jisuapi/weather";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("city", city);
        params.add("appkey","f89330d75709e6c1029839d97aa0004c");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<Object> forEntity = restTemplate.getForEntity(uri, Object.class);
        return forEntity.getBody();
    }

}
