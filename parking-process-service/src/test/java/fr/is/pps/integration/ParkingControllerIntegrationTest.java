package fr.is.pps.integration;

import fr.is.pps.dto.PositionDto;
import fr.is.pps.integration.config.TestConfig;
import fr.is.pps.model.ParkingModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class ParkingControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetListNearbyCarParks() {
        PositionDto positionDto = new PositionDto();
        positionDto.setCountry("France");
        positionDto.setLatitude(48.858093);
        positionDto.setLongitude(2.294694);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String json = "{\"country\":\"Poitiers\",\"latitude\":46.58358353103216,\"longitude\":0.3348348830917244}";
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<List<ParkingModel>> response = restTemplate.exchange(
                "http://localhost:8080/api/nearby-parkings", HttpMethod.GET, request,
                new ParameterizedTypeReference<List<ParkingModel>>() {}, positionDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        List<ParkingModel> parkingList = response.getBody();
        Assertions.assertNotNull(parkingList);
        Assertions.assertFalse(parkingList.isEmpty());
    }
}
