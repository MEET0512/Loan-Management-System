package com.patel.LoanSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.LoanSystem.dto.personalInfoRequest;
import com.patel.LoanSystem.entity.PersonalInfo;
import com.patel.LoanSystem.service.PersonalInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer/personalinfo")
@RequiredArgsConstructor
public class PersonalInfoController {

	private final PersonalInfoService service;
	
	@GetMapping("/{username}")
	public ResponseEntity<PersonalInfo> getPersonalInfo(@PathVariable("username") String username) {
		PersonalInfo personalInfo = service.getPersonalInfo(username);
		
		if(personalInfo == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(personalInfo);
	}
	
	@PostMapping("/add")
	public ResponseEntity<PersonalInfo> addPersonalInfo(@RequestBody personalInfoRequest Request) {
		PersonalInfo personalInfo = service.addOrUpdatePersonalInfo(Request);
		
		if(personalInfo == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(personalInfo);
	}
	
	@PutMapping("/update")
	public ResponseEntity<PersonalInfo> updatePersonalInfo(@RequestBody personalInfoRequest Request) {
		PersonalInfo personalInfo = service.addOrUpdatePersonalInfo(Request);
		
		if(personalInfo == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(personalInfo);
	}
	
	@DeleteMapping("/{infoId}")
	public ResponseEntity<String> deletePersonalInfo(@PathVariable("infoId") Long infoId) {
		try {
			service.deletePersonalInfo(infoId);
			return ResponseEntity.ok("Personal Infomation is deleted.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.ok("There is some problem while deleting personal infomation.");
		}
	}
}
