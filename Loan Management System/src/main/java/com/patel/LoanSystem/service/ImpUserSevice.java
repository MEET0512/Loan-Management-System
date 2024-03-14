package com.patel.LoanSystem.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.patel.LoanSystem.dto.UserRequest;
import com.patel.LoanSystem.entity.User;
import com.patel.LoanSystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImpUserSevice implements UserService {

	private final UserRepository userRepo;
	private final JwtService jwtService;
	
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return (UserDetails) userRepo.findByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
			}
		};
	}

	@Override
	public User getUserDetails(String token) {
		String username = jwtService.extractUsername(token);
		return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
	}

	@Override
	public User getUser(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
	}

	@Override
	public User updateUser(Long userId, UserRequest request) {
		User existedUser = userRepo.findById(userId).orElse(null);
		
		if(existedUser != null) {
			existedUser = User.builder()
							.username(request.getUsername())
							.firstName(request.getFirstName())
							.lastName(request.getLastName())
							.email(request.getEmail())
							.dateOfBirth(request.getDateOfBirth())
							.phoneNumber(request.getPhoneNumber())
							.address(request.getAddress())
							.build();
			
			userRepo.save(existedUser);
		} 
		
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		try {
			User user = userRepo.findById(userId)
					.orElseThrow(() -> new UsernameNotFoundException("User not found."));
			userRepo.delete(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
