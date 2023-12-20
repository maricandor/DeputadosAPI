package com.example.ifsul.produtosBanco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Método para configurar as permissões CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configuração para permitir qualquer origem ("*") e todos os métodos HTTP
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}
