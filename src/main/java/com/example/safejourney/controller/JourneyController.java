package com.example.safejourney.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safejourney.entities.User;
import com.example.safejourney.entities.dto.AlertRequest;
import com.example.safejourney.entities.dto.EndJourneyRequest;
import com.example.safejourney.entities.dto.JourneyResponse;
import com.example.safejourney.entities.dto.StartJourneyRequest;
import com.yugesh.safejourney.service.JourneyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/journey")

public class JourneyController {
      @Autowired
     JourneyService journeyService;

    // START JOURNEY
    @PostMapping("/start")
    public ResponseEntity<JourneyResponse> startJourney(
            @Valid @RequestBody StartJourneyRequest request) {

        return ResponseEntity.ok(journeyService.startJourney(request));
    }

    // END JOURNEY
    @PostMapping("/end")
    public ResponseEntity<JourneyResponse> endJourney(
            @Valid @RequestBody EndJourneyRequest request) {

        return ResponseEntity.ok(journeyService.endJourney(request));
    }

    // TRIGGER ALERT
    @PostMapping("/alert")
    public ResponseEntity<JourneyResponse> triggerAlert(
            @Valid @RequestBody AlertRequest request) {

        return ResponseEntity.ok(journeyService.triggerAlert(request));
    }

    // GET JOURNEY BY TOKEN
    @GetMapping("/{token}")
    public ResponseEntity<JourneyResponse> getJourney(
            @PathVariable String token) {

        return ResponseEntity.ok(journeyService.getJourneyByToken(token));
    }
    
    @GetMapping("/status")
    public ResponseEntity<?> getJourneyStatus(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        boolean active = journeyService.hasActiveJourney(user.getId());

        return ResponseEntity.ok(Map.of("active", active));
    }
}