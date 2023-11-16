package com.example.multiplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.NoSuchElementException;
import java.util.Properties;

@Configuration
public class ErrorConfig {
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();

        Properties properties = new Properties();
        properties.setProperty(NoSuchElementException.class.getSimpleName(), "/error/error404");

        resolver.setExceptionMappings(properties);

        return resolver;
    }

}
