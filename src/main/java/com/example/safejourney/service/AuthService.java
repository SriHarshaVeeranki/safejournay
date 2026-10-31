package com.example.safejourney.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.safejourney.config.JwtUtil;
import com.example.safejourney.entities.OtpVerification;
import com.example.safejourney.entities.User;
import com.example.safejourney.entities.dto.LoginRequest;
import com.example.safejourney.entities.dto.OtpRequest;
import com.example.safejourney.entities.dto.OtpVerifyRequest;
import com.example.safejourney.entities.dto.RegisterRequest;
import com.example.safejourney.exception.ConflictException;
import com.example.safejourney.exception.ResourceNotFoundException;
import com.example.safejourney.exception.UnauthorizedException;
import com.example.safejourney.repository.OtpRepository;
import com.example.safejourney.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    // REGISTER
    public String register(RegisterRequest request) {

        // check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new ConflictException("Email already registered");
        }
        
        // check if phone number already exists
        if (userRepository.findByPhoneNumber(request.getPhoneNumber()).isPresent()) {
            throw new ConflictException("Phone number already registered");
        }

        // create user
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();
        
        userRepository.save(user);

        return "User registered successfully";
    }

    // LOGIN
    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
    
    public String sendOtp(OtpRequest request) {

        String email = request.getEmail();

        String otp = String.valueOf(
                (int) (Math.random() * 900000) + 100000
        );

        Optional<OtpVerification> existingOtp =
                otpRepository.findByEmail(email);

        OtpVerification otpEntity;

        if (existingOtp.isPresent()) {

            // Update existing OTP
            otpEntity = existingOtp.get();

            otpEntity.setOtp(otp);

            otpEntity.setExpiryTime(
                    LocalDateTime.now().plusMinutes(5)
            );

        } else {

            // Create new OTP
            otpEntity = OtpVerification.builder()
                    .email(email)
                    .otp(otp)
                    .expiryTime(LocalDateTime.now().plusMinutes(5))
                    .build();
        }

        otpRepository.save(otpEntity);

        // Send OTP through email
        emailService.sendOtpEmail(email, otp);

        return "OTP sent successfully";
    
    }
    public String verifyOtp(OtpVerifyRequest request) throws BadRequestException {

        OtpVerification otpData = otpRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("OTP not found"));

        if (otpData.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("OTP expired");
        }

        if (!otpData.getOtp().equals(request.getOtp())) {
            throw new BadRequestException("Invalid OTP");
        }

        otpRepository.deleteByEmail(request.getEmail());

        return "Login successful via OTP";
    }
}