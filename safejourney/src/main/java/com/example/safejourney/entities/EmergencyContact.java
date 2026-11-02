package com.example.safejourney.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(
    name = "emergency_contact",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "email"}),
        @UniqueConstraint(columnNames = {"user_id", "phone_number"})
    }
)
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
@Data
public class EmergencyContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String relation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}