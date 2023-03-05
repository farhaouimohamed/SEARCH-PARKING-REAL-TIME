package fr.is.pds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ParkingDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingDiscoveryServiceApplication.class, args);
    }

}
