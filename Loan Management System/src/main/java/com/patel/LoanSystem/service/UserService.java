package com.patel.LoanSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.patel.LoanSystem.dto.UserRequest;
import com.patel.LoanSystem.entity.User;

public interface UserService {

	public UserDetailsService userDetailsService();

	public User getUserDetails(String token);
	
	public User getUser(String username);

	public User updateUser(Long userId, UserRequest request);

	public void deleteUser(Long userId);
}