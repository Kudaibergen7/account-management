package com.example.accountmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Configuration
    public class MappingConfig {

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

    }
}

