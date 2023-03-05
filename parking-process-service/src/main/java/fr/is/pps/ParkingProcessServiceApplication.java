package fr.is.pps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ParkingProcessServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkingProcessServiceApplication.class, args);
    }

}
