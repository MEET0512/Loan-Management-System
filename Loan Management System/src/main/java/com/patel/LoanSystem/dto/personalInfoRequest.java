package com.patel.LoanSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class personalInfoRequest {

	private String ssn;
    private String occupation;
    private String employer;
    private Double monthlyIncome;
    private String otherDetails;
    private String username;
}
