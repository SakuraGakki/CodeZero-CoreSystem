package com.codezero.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，使用配置类形式，直接构造bean，使用注解完成Ordered接口功能,统计接口调用时间
 */
@Slf4j
@Configuration
public class GlobalGatewayFilterConfig
{
    @Bean
    @Order(-100)
    public GlobalFilter elapsedGlobalFilter()
    {
        return (exchange, chain) -> {
            //调用请求之前统计时间
            Long startTime = System.currentTimeMillis();
            return chain.filter(exchange).then().then(Mono.fromRunnable(() -> {
                //调用请求之后统计时间
                Long endTime = System.currentTimeMillis();
                log.info("接口["+exchange.getRequest().getURI().getRawPath()+"]用时："+(endTime - startTime)+"ms");
            }));
        };
    }
}
