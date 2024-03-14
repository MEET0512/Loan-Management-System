package com.patel.LoanSystem.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.patel.LoanSystem.dto.LoanRequest;
import com.patel.LoanSystem.entity.Loan;
import com.patel.LoanSystem.entity.User;
import com.patel.LoanSystem.repository.LoanRepository;
import com.patel.LoanSystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImpLoanService implements LoanService {

	private final UserRepository userRepository;
	private final LoanRepository loanRepository;
	private JavaMailer javaMailer;
	
	@Override
	public Loan addLoan(LoanRequest loanRequest) {
		try {
			
		Optional<User> user = userRepository.findById(loanRequest.getUserId());
		
		if(user.isEmpty()) {
			return null;
		}
		
		String loanNumber = generateLoanNumber();
		
		Loan newLoan = Loan.builder()
							.amount(loanRequest.getAmount())
							.applicationDate(loanRequest.getApplicationDate())
							.interestRate(loanRequest.getInterestRate())
							.term(loanRequest.getTerm())
							.status(loanRequest.getStatus())
							.user(user.get())
							.LoanNumber(loanNumber)
							.build();
		
		loanRepository.save(newLoan);
		
		String response = javaMailer.sendEmail(user.get().getEmail(), user.get().getFirstName(), loanNumber);
		
		return newLoan;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String generateLoanNumber() {
		String prefix = "LN";
		String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyMMdd"));
		String uniquePart = UUID.randomUUID().toString().substring(0, 8);
		return prefix + datePart + uniquePart;
	}

	@Override
	public List<Loan> getAllLoans(Long userId) {
		try {
			List<Loan> allLoan = loanRepository.findByUserId(userId);
			return allLoan;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Loan getLoan(Long loanId) {
		try {
			Loan loan = loanRepository.findById(loanId).get();
			return loan;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Loan> getAllLoanApplications() {
		try {
			List<Loan> allLoans = loanRepository.findAll();
			return allLoans;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean approveLoanApplication(Long loanId) {
		try {
			Optional<Loan> loan = loanRepository.findById(loanId);
			if(loan.get().getStatus() != "New") {
				return false;
			}
			
			loan.get().setStatus("Approved");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean rejectLoanApplication(Long loanId) {
		try {
			Optional<Loan> loan = loanRepository.findById(loanId);
			if(loan.get().getStatus() != "New") {
				return false;
			}
			
			loan.get().setStatus("Rejected");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}