package com.patel.LoanSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patel.LoanSystem.dto.PaymentRequest;
import com.patel.LoanSystem.entity.Loan;
import com.patel.LoanSystem.entity.Payment;
import com.patel.LoanSystem.repository.LoanRepository;
import com.patel.LoanSystem.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImpUserPaymentService implements UserPaymentService {

	private final LoanRepository loanRepository;
	private final PaymentRepository paymentRepository;
	
	@Override
	public Payment loanPayment(PaymentRequest paymentRequest) {
		Optional<Loan> loan = loanRepository.findById(paymentRequest.getLoanId());
		
		if(loan.isEmpty()) {
			return null;
		}
		
		try {
			Payment newPayment = Payment.builder()
										.amount(paymentRequest.getAmount())
										.paymentDate(new Date())
										.loan(loan.get())
										.build();
			
			return paymentRepository.save(newPayment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Payment> getAllPayment(Long loanId) {
		try {
			List<Payment> allPayment = paymentRepository.findByLoanId(loanId);
			
			return allPayment;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}