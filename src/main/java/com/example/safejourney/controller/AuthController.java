package com.example.safejourney.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.safejourney.entities.dto.LoginRequest;
import com.example.safejourney.entities.dto.OtpRequest;
import com.example.safejourney.entities.dto.OtpVerifyRequest;
import com.example.safejourney.entities.dto.RegisterRequest;
import com.example.safejourney.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
     AuthService authService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
    	     System.out.println("register servlet!!!!!!!!!!");
        String response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
    	System.out.println("login api");
        String response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@Valid @RequestBody OtpRequest request) {
        return ResponseEntity.ok(authService.sendOtp(request));
    }
    
    
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@Valid @RequestBody OtpVerifyRequest request) {
        return ResponseEntity.ok(authService.verifyOtp(request));
    }
}

//@Valid:
//
//	Triggers DTO validation automatically
//
//	If invalid:
//	400 Bad Request