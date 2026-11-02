package com.example.safejourney.entities.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
}