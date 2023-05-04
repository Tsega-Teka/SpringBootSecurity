package com.SpringBootSecurity.securty;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.SpringBootSecurity.Entity.User;
import com.SpringBootSecurity.JPARepo.UserRepository;

public class UserDetailsServiceImp implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImp.class);
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOGGER.trace("Entering methood loadUserByUsername" );
		LOGGER.debug("Authenticating user with user name");
		
		
		Optional<User> userObje = userRepo.findByUserName(userName);
		
		return userObje.map(UserDetailsImp :: new ).orElseThrow(() -> new UsernameNotFoundException("user is not found") );
	
	}

}
