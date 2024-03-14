package com.patel.LoanSystem.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Loan {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private Double amount;
    private Double interestRate;
    private Integer term;
    private String status;
    private Date applicationDate;
    private Date approvalDate;
    private Date rejectionDate;
    private String LoanNumber;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
