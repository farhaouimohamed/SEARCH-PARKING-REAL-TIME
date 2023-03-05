package fr.is.pps.openfeign;

import fr.is.pps.model.ParkingModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * A Feign client for retrieving parking data from the parking management service.
 */
@FeignClient(name = "parking-management-service")
public interface ParkingRestClient {

    /**
     * Retrieves a list of parkings for a given country from the parking management service.
     *
     * @param country The name of the country for which to retrieve the list of parkings.
     * @return A list of {@link ParkingModel} objects representing the parkings in the specified country.
     */
    @GetMapping(path = "/api/list-parkings")
    List<ParkingModel> getListParkings(@RequestParam(name = "country") String country);
}
