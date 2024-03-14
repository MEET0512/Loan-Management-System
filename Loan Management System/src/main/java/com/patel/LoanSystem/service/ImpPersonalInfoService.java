package com.patel.LoanSystem.service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import com.patel.LoanSystem.dto.personalInfoRequest;
import com.patel.LoanSystem.entity.PersonalInfo;
import com.patel.LoanSystem.entity.User;
import com.patel.LoanSystem.repository.PersonalInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImpPersonalInfoService implements PersonalInfoService {

	private final UserService userService;
	private final PersonalInfoRepository infoRepository;
	
	@Override
	public PersonalInfo getPersonalInfo(String username) {
		User user = userService.getUser(username);
		PersonalInfo info = infoRepository.findByUserId(user.getUserId());
		return info;
	}

	@Override
	public PersonalInfo addOrUpdatePersonalInfo(personalInfoRequest request) {
		User user = userService.getUser(request.getUsername());
		PersonalInfo Existinfo = infoRepository.findByUserId(user.getUserId());
		
		if(Existinfo == null) {
			PersonalInfo newInfo = PersonalInfo.builder()
												.ssn(request.getSsn())
												.occupation(request.getOccupation())
												.employer(request.getEmployer())
												.monthlyIncome(request.getMonthlyIncome())
												.otherDetails(request.getOtherDetails())
												.user(user)
												.build();
			return infoRepository.save(newInfo);
		}
		
		Existinfo = PersonalInfo.builder()
								.ssn(request.getSsn())
								.occupation(request.getOccupation())
								.employer(request.getEmployer())
								.monthlyIncome(request.getMonthlyIncome())
								.otherDetails(request.getOtherDetails())
								.build();
		return infoRepository.save(Existinfo);
		
		
	}

	@Override
	public void deletePersonalInfo(Long infoId) {
		try {
			PersonalInfo info = infoRepository.findById(infoId)
									.orElseThrow(() -> new AccountNotFoundException());
			infoRepository.delete(info);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
