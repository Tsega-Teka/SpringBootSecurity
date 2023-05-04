package com.SpringBootSecurity.JPARepo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringBootSecurity.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	 
	Optional<User> findByUserName(String userName);

}
