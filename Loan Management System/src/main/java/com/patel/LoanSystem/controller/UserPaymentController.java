package com.patel.LoanSystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.LoanSystem.dto.PaymentRequest;
import com.patel.LoanSystem.entity.Payment;
import com.patel.LoanSystem.service.UserPaymentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class UserPaymentController {

	private final UserPaymentService userPaymentService;
	
	@PostMapping("/loanPayment")
	public ResponseEntity<?> loanPayment(@RequestBody PaymentRequest paymentRequest) {
		Payment payment = userPaymentService.loanPayment(paymentRequest);
		
		if(payment == null) {
			return ResponseEntity.badRequest().body("There is some problem while we processing your payment.");
		}
		
		return ResponseEntity.ok(payment);
	}
	
	@GetMapping("/payment/{loanId}")
	public ResponseEntity<?> getAllPayment(@PathVariable("loanId") Long loanId) {
		List<Payment> allPayments = userPaymentService.getAllPayment(loanId);
		
		if(allPayments.isEmpty()) {
			return ResponseEntity.badRequest().body("There is some problem while fetching paymets of loan");
		}
		
		return ResponseEntity.ok(allPayments);
	}
}
