package fr.is.pm.service;

import fr.is.pm.model.ParkingModel;

import java.util.List;

public interface ParkingService {
    public List<ParkingModel> getParkingsInRealTime(String country);
}
