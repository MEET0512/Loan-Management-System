package com.patel.LoanSystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.LoanSystem.dto.LoanRequest;
import com.patel.LoanSystem.entity.Loan;
import com.patel.LoanSystem.service.LoanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class UserLoanController {

	private final LoanService service;
	
	@PostMapping("/applyLoan")
	public ResponseEntity<?> applyLoan(@RequestBody LoanRequest loanRequest) {
		Loan newLoan = service.addLoan(loanRequest);
		
		if (newLoan == null) {
			return ResponseEntity.badRequest().body("There are some problem while applying for loan! Please, try again."); 
		}
		
		return ResponseEntity.ok(newLoan);
	}
	
	@GetMapping("/loan/{userId}")
	public ResponseEntity<?> getAllLoans(@PathVariable("userId") Long userId) {
		List<Loan> allLoans = service.getAllLoans(userId);
		
		if(allLoans.isEmpty()) {
			return ResponseEntity.badRequest().body("There is some problem while fetching Loans.");
		}
		
		return ResponseEntity.ok(allLoans);
	}
	
	@GetMapping("/loan/{loanId}")
	public ResponseEntity<?> getLoanDetails(@PathVariable("loanId") Long loanId) {
		Loan loan = service.getLoan(loanId);
		
		if(loan == null) {
			return ResponseEntity.badRequest().body("There is some problem while fetching Loan Details.");
		}
		
		return ResponseEntity.ok(loan);
	}
}
