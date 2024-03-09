package br.com.api.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMaperConfig {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
