package com.patel.LoanSystem.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

	private Double amount;
    private Double interestRate;
    private Integer term;
    private String status;
    private Date applicationDate;
    private Date approvalDate;
    private Date rejectionDate;
    private Long userId;
}
