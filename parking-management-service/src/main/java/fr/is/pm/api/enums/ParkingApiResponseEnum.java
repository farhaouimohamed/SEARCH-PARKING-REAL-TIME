package fr.is.pm.api.enums;

import fr.is.pm.api.model.ParkingApiResponse;
import fr.is.pm.api.model.poitiers.ParkingPoitiersApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * Enum representing different parking API responses.
 */
@AllArgsConstructor
public enum ParkingApiResponseEnum {

    /**
     * The parking API response for Poitiers.
     */
    @Getter
    POITIERS(ParkingPoitiersApiResponse.class.getSimpleName());

    private final String implementationClass;

    /**
     * Returns the {@code ParkingApiResponseEnum} corresponding to the specified implementation class.
     *
     * @param implementationClass The implementation class for which to get the {@code ParkingApiResponseEnum}.
     * @return The {@code ParkingApiResponseEnum} corresponding to the specified implementation class, or {@code null} if none is found.
     */
    public static ParkingApiResponseEnum valueOfC(String implementationClass) {
        if (implementationClass == null) return null;
        return Stream.of(values()).filter(e -> e.implementationClass.equals(implementationClass)).findFirst().orElse(null);
    }

}
