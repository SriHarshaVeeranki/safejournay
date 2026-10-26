package com.example.safejourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safejourney.entities.dto.LocationRequest;
import com.example.safejourney.entities.dto.LocationResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/location")

public class LocationController {
     @Autowired
     LocationService locationService;

    @PostMapping(consumes="application/json")
    public ResponseEntity<String> saveLocation(@RequestBody @Valid LocationRequest request) {
        locationService.saveLocation(request);
        return ResponseEntity.ok("Location saved successfully");
    }

    @GetMapping("/{token}")
    public ResponseEntity<LocationResponse> getLatestLocation(@PathVariable String token) {
        return ResponseEntity.ok(locationService.getLatestLocation(token));
    }
}