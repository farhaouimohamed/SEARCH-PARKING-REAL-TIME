package fr.is.pm.service.impl;

import fr.is.pm.data.ParkingDataAccess;
import fr.is.pm.model.ParkingModel;
import fr.is.pm.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Provides business logic for managing parking lot information.
 */
@Service
public class ParkingServiceImpl implements ParkingService {

    private ParkingDataAccess parkingDataAccess;

    /**
     * Constructs a new instance of the {@code ParkingLotService} class with the specified data access object.
     *
     * @param parkingDataAccess The data access object to use for retrieving parking lot information.
     */
    public ParkingServiceImpl(ParkingDataAccess parkingDataAccess){
        super();
        this.parkingDataAccess = parkingDataAccess;
    }

    /**
     * Retrieves a list of parking lots associated to a country.
     *
     * @param country The latitude of the location.
     * @return A list of parking lots associated to a country.
     */
    @Override
    public List<ParkingModel> getParkingsInRealTime(String country) {
        return parkingDataAccess.getAllParkingsInRealTime(country);
    }

}
