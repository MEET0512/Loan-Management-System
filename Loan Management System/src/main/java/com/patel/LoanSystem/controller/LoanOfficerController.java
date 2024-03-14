package com.patel.LoanSystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.LoanSystem.entity.Loan;
import com.patel.LoanSystem.service.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/officer")
@RequiredArgsConstructor
public class LoanOfficerController {

	private final LoanService loanService;
	
	@GetMapping("/loan-applications")
    public ResponseEntity<?> getAllLoanApplications() {
        try {
        	List<Loan> allLoans = loanService.getAllLoanApplications();
            if(allLoans.isEmpty()) {
            	return ResponseEntity.noContent().build();
            } else {
            	return ResponseEntity.ok(allLoans);
            }
        } catch (Exception e) {
			return ResponseEntity.badRequest().body("There are some problem when fetching loans");
		}
    }
	
	// Endpoint to approve a loan application
    @PostMapping("/approve/{loanId}")
    public ResponseEntity<?> approveLoanApplication(@PathVariable("loanId") Long loanId) {
        if (loanService.approveLoanApplication(loanId)) {
            return ResponseEntity.ok("Loan application approved successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to approve loan application");
        }
    }

    // Endpoint to reject a loan application
    @PostMapping("/reject/{loanId}")
    public ResponseEntity<?> rejectLoanApplication(@PathVariable Long loanId) {
        if (loanService.rejectLoanApplication(loanId)) {
            return ResponseEntity.ok("Loan application rejected successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to reject loan application");
        }
    }
}
