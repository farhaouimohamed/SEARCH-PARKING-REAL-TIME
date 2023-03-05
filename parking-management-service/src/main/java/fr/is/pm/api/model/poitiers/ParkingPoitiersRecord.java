package fr.is.pm.api.model.poitiers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class ParkingPoitiersRecord {

    @JsonProperty("fields")
    private ParkingPoitiersField parkingPoitiersField;

}
