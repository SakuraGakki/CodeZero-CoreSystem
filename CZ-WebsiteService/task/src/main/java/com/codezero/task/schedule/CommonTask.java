package com.codezero.task.schedule;

import com.codezero.task.service.CommonTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonTask {

    @Autowired
    CommonTaskService commonTaskService;

    /**
     * 小程序首页信息同步接口(头条)更新周期：1小时
     */
    @Scheduled(cron = "0 0 */1 * * *")
    public void getNewsInfoTask() {
        log.info("获取头条任务开始");
        commonTaskService.getNewsInfoTask();
    }

    /**
     * 小程序首页信息同步接口(热搜)更新周期：3小时
     */
    @Scheduled(cron = "0 0 */3 * * *")
    public void getKeywordsInfoTask() {
        log.info("获取热搜任务开始");
        commonTaskService.getKeywordsInfoTask();
    }

    /**
     * 小程序首页信息同步接口(黄历)更新周期：每天
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void getCalendarInfoTask() {
        log.info("获取黄历任务开始");
        commonTaskService.getCalendarInfoTask();
    }

}
