package com.example.sessiontutorial.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {

    private Map<String, String> dns;

}