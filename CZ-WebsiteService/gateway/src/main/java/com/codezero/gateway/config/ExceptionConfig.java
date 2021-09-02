package com.codezero.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

@Configuration
@Slf4j
public class ExceptionConfig {
    /**
     * 自定义异常处理[@@]注册Bean，会从容器中直接获取，所以直接注入即可
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer){
        GlobalGatewayExceptionHandler globalGatewayExceptionHandler = new GlobalGatewayExceptionHandler();
        globalGatewayExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        globalGatewayExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        globalGatewayExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        log.debug("Init GlobalGatewayExceptionHandler Instead DefaultErrorWebExceptionHandler Success");
        return globalGatewayExceptionHandler;
    }
}
