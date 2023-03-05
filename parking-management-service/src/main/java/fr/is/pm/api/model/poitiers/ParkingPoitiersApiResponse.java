package fr.is.pm.api.model.poitiers;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.is.pm.api.model.ParkingApiResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Builder @AllArgsConstructor @NoArgsConstructor
public class ParkingPoitiersApiResponse implements ParkingApiResponse {
    @JsonProperty("records")
    private List<ParkingPoitiersRecord> records;
}
