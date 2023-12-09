package com.example.multiplace.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDate> toLocalDate = s -> s.getSource() == null ? null
                : LocalDate.parse((s.getSource()), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        modelMapper.addConverter(toLocalDate);


        return modelMapper;
    }
}
