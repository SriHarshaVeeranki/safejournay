package com.example.safejourney.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.safejourney.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
//	For User, the common entry points are:
//	 - Email → login / registration
//   - Phone number → login / OTP
//   - ID → internal system usage
	Optional<User> findByEmail(String email);
	Optional<User> findByPhoneNumber(String phonenumber);

}