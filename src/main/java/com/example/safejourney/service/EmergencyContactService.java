package com.example.safejourney.service;

import java.util.List;

import com.example.safejourney.entities.dto.ContactRequest;
import com.example.safejourney.entities.dto.ContactResponse;

public interface EmergencyContactService {

    void addContact(ContactRequest request);

    List<ContactResponse> getMyContacts();

    void updateContact(Long contactId, ContactRequest request);

    void deleteContact(Long contactId);
}