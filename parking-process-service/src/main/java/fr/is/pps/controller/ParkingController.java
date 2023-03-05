package fr.is.pps.controller;

import fr.is.pps.dto.PositionDto;
import fr.is.pps.model.ParkingModel;
import fr.is.pps.payload.ApiResponse;
import fr.is.pps.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing parking data.
 */
@RestController
@RequestMapping("/api")
public class ParkingController {

    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    /**
     * Retrieves a list of nearby car parks based on the given position.
     *
     * @param positionDto The position from which to retrieve the list of nearby car parks.
     * @return A {@link ResponseEntity} containing the list of nearby car parks.
     */
    @GetMapping("/nearby-parkings")
    public ResponseEntity<?> getListNearbyCarParks(@RequestBody PositionDto positionDto){
        try {
            return ResponseEntity.ok(parkingService.getListNearbyCarParks(positionDto));
        } catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(new ApiResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
