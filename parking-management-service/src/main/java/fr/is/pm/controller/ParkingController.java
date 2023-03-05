package fr.is.pm.controller;

import fr.is.pm.model.ParkingModel;
import fr.is.pm.payload.ApiResponse;
import fr.is.pm.service.ParkingService;
import fr.is.pm.service.impl.ParkingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provides the presentation layer for managing parking lot information.
 */
@RestController
@RequestMapping(path = "/api")
public class ParkingController {
    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    /**
     * Retrieves a list of parking lots associated to a country.
     *
     * @param country The latitude of the location.
     * @return A list of parking lots associated to a country.
     */
    @GetMapping("/list-parkings")
    public ResponseEntity<?> getParkingsInRealTime(@RequestParam("country") String country){
        try {
            return ResponseEntity.ok(parkingService.getParkingsInRealTime(country));
        } catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

}
