package fr.is.pm.api.factory;

import fr.is.pm.api.model.ParkingApiResponse;
import fr.is.pm.api.model.poitiers.ParkingPoitiersApiResponse;
import fr.is.pm.enums.CountryEnum;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Component class for creating parking API responses using factory design pattern.
 */
@Component
public class ParkingApiResponseFactory {

    private Map<String, ParkingApiResponse> parkingApiResponseMap = new HashMap<>();

    @Autowired
    private ParkingPoitiersApiResponse parkingPoitiersApiResponse;

    /**
     * Initializes the factory by adding the parking API response for Poitiers to the response map
     * Every country that we will add, we need to create the corresponding model (ex: package model.poitiers ...)
     * based on the url content and add the new country to CountryEnym then put the new valuer in the map.
     */
    @PostConstruct
    private void init(){
        parkingApiResponseMap.put(CountryEnum.POITIERS.label, parkingPoitiersApiResponse);
    }

    public ParkingApiResponse create(String country){
        return parkingApiResponseMap.get(country);
    }
}
