package com.SpringBootSecurity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.SpringBootSecurity.Entity.User;
import com.SpringBootSecurity.JPARepo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	public User saveuser(User user1) {
		
		user1.setPassword(encoder.encode(user1.getPassword()));
		
		return userRepo.save(user1);
		
	}
	
}
