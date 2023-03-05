package fr.is.pps.dto;

import lombok.Data;

/**
 * Data transfer object for a geographic position.
 */
@Data
public class PositionDto {
    private String country;
    private Double latitude;
    private Double longitude;
}
