package com.example.safejourney.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.safejourney.entities.OtpVerification;

public interface OtpRepository extends JpaRepository<OtpVerification, Long>{
	
	Optional<OtpVerification> findByEmail(String email);

    void deleteByEmail(String email);

}