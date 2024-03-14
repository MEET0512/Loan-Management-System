package com.patel.LoanSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonalInfo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personalInfoId;

    private String ssn;
    private String occupation;
    private String employer;
    private Double monthlyIncome;
    private String otherDetails;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
