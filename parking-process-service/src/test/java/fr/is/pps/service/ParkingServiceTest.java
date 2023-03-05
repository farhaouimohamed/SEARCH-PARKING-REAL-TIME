package fr.is.pps.service;

import fr.is.pps.dto.PositionDto;
import fr.is.pps.model.ParkingModel;
import fr.is.pps.openfeign.ParkingRestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ParkingServiceTest {

    @Mock
    private ParkingRestClient parkingRestClient;

    @InjectMocks
    private ParkingService parkingService;

    @Test
    public void testGetListNearbyCarParks(){
        PositionDto positionDto = new PositionDto();
        positionDto.setCountry("Poitiers");
        positionDto.setLatitude(46.58358353103216);
        positionDto.setLongitude(0.3348348830917244);

        List<ParkingModel> parkingList = new ArrayList<>();
        ParkingModel parking1 = new ParkingModel();
        parking1.setLatitude(46.57505317559496);
        parking1.setLongitude(0.337126307915689);
        parkingList.add(parking1);

        ParkingModel parking2 = new ParkingModel();
        parking2.setLatitude(46.5793235337795);
        parking2.setLongitude(0.3385507838016221);
        parkingList.add(parking2);

        Mockito.when(parkingRestClient.getListParkings(positionDto.getCountry()))
                .thenReturn(parkingList);

        List<ParkingModel> result = parkingService.getListNearbyCarParks(positionDto);

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(964, result.get(0).getDistance().intValue());
        Assertions.assertEquals(552, result.get(1).getDistance().intValue());
    }
}
