package net.focik.employee.domain;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TeamConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
