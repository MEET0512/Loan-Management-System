package com.patel.LoanSystem.service;

import java.util.List;

import com.patel.LoanSystem.dto.PaymentRequest;
import com.patel.LoanSystem.entity.Payment;

public interface UserPaymentService {

	Payment loanPayment(PaymentRequest paymentRequest);

	List<Payment> getAllPayment(Long loanId);

}
