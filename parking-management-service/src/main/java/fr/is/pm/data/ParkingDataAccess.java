package fr.is.pm.data;

import fr.is.pm.api.enums.ParkingApiResponseEnum;
import fr.is.pm.api.model.ParkingApiResponse;
import fr.is.pm.api.model.poitiers.ParkingPoitiersApiResponse;
import fr.is.pm.api.model.poitiers.ParkingPoitiersField;
import fr.is.pm.api.factory.ParkingApiResponseFactory;
import fr.is.pm.exception.ResourceNotFoundException;
import fr.is.pm.model.ParkingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class provides data access to parking lot information from a remote api.
 */
@Component
public class ParkingDataAccess{
    @Value("${parking.realtime.data.poitiers.url}")
    private String parkingPoitiersRealtimeDataUrl;
    private RestTemplate restTemplate;
    private ParkingApiResponseFactory parkingApiResponseFactory;

    /**
     * Constructs a new instance of the {@code ParkingDataAccess} class with the specified URL and {@code RestTemplate}.
     *
     * @param parkingApiResponseFactory The factory class that creates the corresponding ParkingApiResponse implementation
     *         based on the country.
     * @param restTemplate The {@code RestTemplate} to use for making HTTP requests.
     */
    public ParkingDataAccess(ParkingApiResponseFactory parkingApiResponseFactory, RestTemplate restTemplate){
        this.parkingApiResponseFactory = parkingApiResponseFactory;
        this.restTemplate = restTemplate;
    }


    /**
     * Retrieves a list of parking lots associated to a country.
     * Every added country we need to :
     *  1-  Create a new case and the corresponding getCountryParkingList method based
     *  2-  Add new attribute parkigCountryRealTimeDataUrl.
     *  3-  Add the new url to application.properties.
     *
     * @param country The latitude of the location.
     * @return A list of parking lots associated to a country.
     */
    public List<ParkingModel> getAllParkingsInRealTime(String country) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ParkingApiResponse parkingApiResponse = parkingApiResponseFactory.create(country);
        List<ParkingModel> parkingModels = new ArrayList<>();
        try {
            switch (ParkingApiResponseEnum.valueOfC(parkingApiResponse.getClass().getSimpleName())){
                case POITIERS:
                    parkingModels = getPoitiersParkingList(restTemplate, parkingPoitiersRealtimeDataUrl, entity);
                    break;
                default:
                    break;
            }
            return parkingModels;
        } catch (Exception e){
            throw new ResourceNotFoundException("ERROR: data not found - "+ e.getMessage());
        }

    }

    private List<ParkingModel> getPoitiersParkingList(RestTemplate restTemplate, String url, HttpEntity<String> entity){
        ResponseEntity<ParkingPoitiersApiResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, ParkingPoitiersApiResponse.class);
        ParkingPoitiersApiResponse parkingPoitiersApiResponse = responseEntity.getBody();
        List<ParkingModel> parkingInfoList = new ArrayList<>();
        if (parkingPoitiersApiResponse != null) {
            parkingInfoList = parkingPoitiersApiResponse.getRecords().stream()
                    .map(record -> {
                        ParkingPoitiersField field = record.getParkingPoitiersField();
                        return ParkingModel.builder().id(field.getId())
                                .parkCarName(field.getName()).availableSpots(field.getFreeSpaces())
                                .latitude(field.getGeoPoint() != null ? field.getGeoPoint().get(0) : 0 )
                                .longitude(field.getGeoPoint() != null ? field.getGeoPoint().get(1) : 0)
                                .capacity(field.getCapacity()).build();
                    }).collect(Collectors.toList());
        }
        return parkingInfoList;
    }
}
