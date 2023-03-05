package fr.is.pps.service;

import fr.is.pps.dto.PositionDto;
import fr.is.pps.exception.ResourceNotFoundException;
import fr.is.pps.model.ParkingModel;
import fr.is.pps.openfeign.ParkingRestClient;
import fr.is.pps.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for parking-related business logic.
 */
@Service
public class ParkingService {

    private ParkingRestClient parkingRestClient;

    public ParkingService(ParkingRestClient parkingRestClient){
        this.parkingRestClient = parkingRestClient;
    }

    /**
     * Retrieves a list of nearby car parks based on the given position.
     * @param positionDto the position of the user
     * @return a list of nearby car parks
     */
    public List<ParkingModel> getListNearbyCarParks(PositionDto positionDto){
        try {
            List<ParkingModel> parkingList = parkingRestClient.getListParkings(positionDto.getCountry());
            parkingList.removeIf(parkingModel -> (parkingModel.getLatitude() == 0 && parkingModel.getLongitude() == 0));
            parkingList.forEach(parking -> {
                parking.setDistance(calculateDistance(positionDto.getLatitude(), positionDto.getLongitude(),
                        parking.getLatitude(), parking.getLongitude()));
            });
            return parkingList;
        } catch (Exception e){
            throw new ResourceNotFoundException("Error while getting data from management-service : " + e.getMessage());
        }

    }

    /**
     * Calculates the distance between two points on a map using the Haversine formula.
     * @param lat1 the latitude of the first point
     * @param lon1 the longitude of the first point
     * @param lat2 the latitude of the second point
     * @param lon2 the longitude of the second point
     * @return the distance between the two points, in meters
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371.0 * 1000.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
