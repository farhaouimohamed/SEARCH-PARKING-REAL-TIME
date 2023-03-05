package fr.is.pm.api.model.poitiers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ParkingPoitiersField {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nom")
    private String name;

    @JsonProperty("etat")
    private String status;

    @JsonProperty("capacite")
    private int capacity;

    @JsonProperty("places")
    private int freeSpaces;

    @JsonProperty("geo_point_2d")
    private List<Double> geoPoint;
}
