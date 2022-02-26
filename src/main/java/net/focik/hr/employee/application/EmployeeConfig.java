package net.focik.hr.employee.application;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
class EmployeeConfig {
    //@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
