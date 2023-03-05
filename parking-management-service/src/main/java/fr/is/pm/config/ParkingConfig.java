package fr.is.pm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for the parking application.
 */
@Configuration
public class ParkingConfig {

    /**
     * Creates a new instance of the {@code RestTemplate} class to be used throughout the application.
     *
     * @return A new instance of the {@code RestTemplate} class.
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
