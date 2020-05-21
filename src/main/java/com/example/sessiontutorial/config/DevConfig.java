package com.example.sessiontutorial.config;

import com.example.sessiontutorial.apinfra.saga.*;
import com.example.sessiontutorial.domain.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DevConfig {

    @Autowired
    ServiceProperties serviceProperties;

    @Bean
    public RestOperations restOperationsForGoods(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri(serviceProperties.getDns().get("goods")).build();

    }

    @Bean
    public RestOperations restOperationsForAccount(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.rootUri(serviceProperties.getDns().get("account")).build();

    }
}
