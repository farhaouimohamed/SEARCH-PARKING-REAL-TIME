package fr.is.pps.model;

import lombok.*;

/**
 * Represents a parking info.
 */
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class ParkingModel {
    private String id;
    private String parkCarName;
    private Double latitude;
    private Double longitude;
    private int availableSpots;
    private int capacity;
    private Double distance;
}
