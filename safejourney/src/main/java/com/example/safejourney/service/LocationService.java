package com.example.safejourney.service;

import com.example.safejourney.entities.dto.LocationRequest;
import com.example.safejourney.entities.dto.LocationResponse;

public interface LocationService {

    void saveLocation(LocationRequest request);

    LocationResponse getLatestLocation(String trackingToken);
}