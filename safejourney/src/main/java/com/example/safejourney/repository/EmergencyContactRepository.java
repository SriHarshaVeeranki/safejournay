package com.example.safejourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.safejourney.entities.EmergencyContact;
import com.example.safejourney.entities.User;

import java.util.List;

public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {

    List<EmergencyContact> findByUser(User user);

    boolean existsByUserAndPhoneNumber(User user, String phoneNumber);

    boolean existsByUserAndEmail(User user, String email);
}