package com.patel.LoanSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.LoanSystem.dto.JwtAuthenticationResponse;
import com.patel.LoanSystem.dto.SignInRequest;
import com.patel.LoanSystem.dto.SignUpRequest;
import com.patel.LoanSystem.entity.User;
import com.patel.LoanSystem.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;
	
	@PostMapping("/singup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
		User user = service.signUp(signUpRequest);
		
		if(user == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/singin")
	public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
		JwtAuthenticationResponse response = service.signIn(signInRequest);
		
		if(response == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(response);
	}
}
