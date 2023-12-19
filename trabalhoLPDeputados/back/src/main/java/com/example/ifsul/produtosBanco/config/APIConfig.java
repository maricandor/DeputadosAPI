package com.example.ifsul.produtosBanco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
@Configuration
public class APIConfig {

    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
}
