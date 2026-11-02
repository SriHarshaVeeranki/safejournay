package com.example.safejourney.service;

import com.example.safejourney.entities.User;

public interface UserService {
	
	public User getByEmail(String email);

}