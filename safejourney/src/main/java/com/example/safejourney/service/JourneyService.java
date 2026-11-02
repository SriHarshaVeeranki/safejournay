package com.example.safejourney.service;

import com.example.safejourney.entities.dto.AlertRequest;
import com.example.safejourney.entities.dto.EndJourneyRequest;
import com.example.safejourney.entities.dto.JourneyResponse;
import com.example.safejourney.entities.dto.StartJourneyRequest;

public interface JourneyService {

    JourneyResponse startJourney(StartJourneyRequest request);

    JourneyResponse endJourney(EndJourneyRequest request);

    JourneyResponse triggerAlert(AlertRequest request);

    JourneyResponse getJourneyByToken(String trackingToken);
    
    boolean hasActiveJourney(Long userId);
}