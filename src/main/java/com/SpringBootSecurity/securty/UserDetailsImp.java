package com.SpringBootSecurity.securty;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.SpringBootSecurity.Entity.User;

public class UserDetailsImp implements UserDetails {

	String userNames ;
	String passwords;
	List<GrantedAuthority> userroles;

	public UserDetailsImp(User user) {
		
		userNames = user.getUserName();
		passwords=  user.getPassword();
		
		userroles= Arrays.stream(user.getRole().split(","))
				.map(SimpleGrantedAuthority :: new)
				.collect(Collectors.toList());

	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return userroles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwords;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userNames;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
