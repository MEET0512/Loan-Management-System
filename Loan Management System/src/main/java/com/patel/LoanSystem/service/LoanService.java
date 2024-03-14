package com.patel.LoanSystem.service;

import java.util.List;

import com.patel.LoanSystem.dto.LoanRequest;
import com.patel.LoanSystem.entity.Loan;

public interface LoanService {

	Loan addLoan(LoanRequest loanRequest);

	List<Loan> getAllLoans(Long userId);

	Loan getLoan(Long loanId);

	List<Loan> getAllLoanApplications();

	boolean approveLoanApplication(Long loanId);

	boolean rejectLoanApplication(Long loanId);

}
