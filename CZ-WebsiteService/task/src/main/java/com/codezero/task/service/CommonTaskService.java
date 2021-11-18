package com.codezero.task.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codezero.task.dao.DaysMapper;
import com.codezero.task.dao.KeywordsMapper;
import com.codezero.task.dao.NewsMapper;
import com.codezero.task.entity.days.DaysResponse;
import com.codezero.task.entity.days.DaysResponseBody;
import com.codezero.task.entity.keyword.KeywordResponse;
import com.codezero.task.entity.keyword.KeywordResponseInfo;
import com.codezero.task.entity.news.NewsResponse;
import com.codezero.task.entity.news.NewsResponseInfo;
import com.codezero.task.pojo.days.Days;
import com.codezero.task.pojo.keywords.Keywords;
import com.codezero.task.pojo.news.News;
import com.codezero.task.uitls.DateUtils;
import com.codezero.task.uitls.NumUtils;
import com.codezero.task.uitls.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class CommonTaskService {

    @Autowired
    NewsMapper newsMapper;
    @Autowired
    KeywordsMapper keywordsMapper;
    @Autowired
    DaysMapper daysMapper;
    @Resource
    private RedisUtil redisUtil;

    public void getNewsInfoTask(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://way.jd.com/jisuapi/get";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("channel", "头条");
        params.add("num", "10");
        params.add("start", "0");
        params.add("appkey", "f89330d75709e6c1029839d97aa0004c");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<NewsResponse> forEntity = restTemplate.getForEntity(uri, NewsResponse.class);
        NewsResponse newsResponse = forEntity.getBody();
        if("查询成功".equals(newsResponse.getMsg()) && newsResponse.getResult().getResult().getList()!=null && newsResponse.getResult().getResult().getList().size()>0){
            List<NewsResponseInfo> list = newsResponse.getResult().getResult().getList();
            List<News> newsList = transformNewsInfoToNews(list);
            redisUtil.set("newsInfo", JSONArray.toJSONString(newsList));
            int i = newsMapper.insertBatch(newsList);
            log.info("redis中新闻信息值："+JSONArray.parseArray(redisUtil.get("newsInfo").toString()).toString());
            if(i==newsList.size()){
                log.info("头条任务全部结束共"+i+"条数据被处理！");
            }else{
                log.info("头条任务全部结束共"+i+"条数据被处理！");
            }
        }
    }

    public void getKeywordsInfoTask(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://way.jd.com/showapi/rcInfo";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("tab", "realtime");
        params.add("category", "全部类型");
        params.add("country", "全部地区");
        params.add("appkey", "f89330d75709e6c1029839d97aa0004c");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<KeywordResponse> forEntity = restTemplate.getForEntity(uri, KeywordResponse.class);
        KeywordResponse keywordResponse = forEntity.getBody();
        if("查询成功".equals(keywordResponse.getMsg()) && keywordResponse.getResult().getShowapi_res_body().getList()!=null && keywordResponse.getResult().getShowapi_res_body().getList().size()>0){
            List<KeywordResponseInfo> list = keywordResponse.getResult().getShowapi_res_body().getList();
            List<Keywords> keywordsList = transformKeywordInfoToKeywords(list);
            redisUtil.set("keywordsInfo", JSONArray.toJSONString(keywordsList));
            int i = keywordsMapper.insertBatch(keywordsList);
            log.info("redis中热搜信息值："+JSONArray.parseArray(redisUtil.get("keywordsInfo").toString()).toString());
            if(i==keywordsList.size()){
                log.info("热搜任务全部结束共"+i+"条数据被处理！");
            }else{
                log.info("热搜任务全部结束共"+i+"条数据被处理！");
            }
        }
    }

    public void getCalendarInfoTask(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://apis.juhe.cn/fapig/calendar/day.php";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", DateUtils.getDateByString());
        params.add("detail", "1");
        params.add("key", "ce58aafc67442967af9b7cffca894b74");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(params).build().encode().toUri();
        ResponseEntity<DaysResponse> forEntity = restTemplate.getForEntity(uri, DaysResponse.class);
        DaysResponse daysResponse = forEntity.getBody();
        log.info(daysResponse.toString());
        if(daysResponse.getError_code()==0){
            DaysResponseBody daysResponseBody = daysResponse.getResult();
            Days days = transformDaysInfoToDays(daysResponseBody);
            redisUtil.set("calendarInfo", JSONObject.toJSONString(days));
            int i = daysMapper.insert(days);
            log.info("redis中日历信息值："+JSONObject.parseObject(redisUtil.get("calendarInfo").toString()).toString());
            if(i==1){
                log.info("黄历任务结束共"+i+"条数据被处理！");
            }else{
                log.info("黄历任务结束共"+i+"条数据被处理！");
            }
        }
    }

    protected List<News> transformNewsInfoToNews(List<NewsResponseInfo> list){
        List<News> newsList = new ArrayList<>();
        for(NewsResponseInfo newsResponseInfo:list){
            News news = new News();
            news.setTitle(newsResponseInfo.getTitle());
            news.setTime(newsResponseInfo.getTime());
            news.setSrc(newsResponseInfo.getSrc());
            news.setCategory(newsResponseInfo.getCategory());
            news.setPic(newsResponseInfo.getPic());
            news.setUrl(newsResponseInfo.getUrl());
            news.setWeburl(newsResponseInfo.getWeburl());
            news.setContent(newsResponseInfo.getContent());
            newsList.add(news);
        }
        return newsList;
    }

    protected List<Keywords> transformKeywordInfoToKeywords(List<KeywordResponseInfo> list){
        List<Keywords> keywordsList = new ArrayList<>();
        for(KeywordResponseInfo keywordResponseInfo:list){
            Keywords keywords = new Keywords();
            keywords.setTime(DateUtils.getCurrentTime());
            keywords.setLevel(keywordResponseInfo.getLevel());
            keywords.setName(keywordResponseInfo.getName());
            keywords.setNum(keywordResponseInfo.getNum());
            keywords.setTrend(keywordResponseInfo.getTrend());
            keywordsList.add(keywords);
        }
        return keywordsList;
    }

    protected Days transformDaysInfoToDays(DaysResponseBody daysResponseBody){
        Days days = new Days();
        days.setDate(daysResponseBody.getDate());
        days.setAnimal(daysResponseBody.getAnimal());
        days.setAvoid(daysResponseBody.getAvoid());
        days.setDesc(daysResponseBody.getDesc());
        days.setGzDate(daysResponseBody.getGzDate());
        days.setGzMonth(daysResponseBody.getGzMonth());
        days.setGzYear(daysResponseBody.getGzYear());
        days.setLDate(NumUtils.int2ChineseNumber(Integer.parseInt(daysResponseBody.getLunarDate())));
        days.setLMonth(NumUtils.int2ChineseNumber(Integer.parseInt(daysResponseBody.getLunarMonth())));
        days.setStatusDesc(daysResponseBody.getStatusDesc());
        days.setSuit(daysResponseBody.getSuit());
        days.setWeek(daysResponseBody.getWeek());
        return days;
    }

}
